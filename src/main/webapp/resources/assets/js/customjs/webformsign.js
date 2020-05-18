(function (w) {

    function format(s) {
        var i = arguments.length;

        while (i-- > 1) {
            s = s.replace(new RegExp('\\{' + (i - 1) + '\\}', 'gm'), arguments[i]);
        }

        return s;
    };

    function formateDate(d) {
        return format("{0}/{1}/{2}", d.getDay(), d.getMonth() + 1, d.getFullYear());
    }

    function getBusyModal(msg, col) {
        var msgHtml = $("<div/>").css({
            display: 'none',
            padding: '50px',
            width: "30%",
            border: "4px solid #3aa061",
            'border-radius': '7px',
            'background-color': 'white'
        });
        msgHtml.html(msg);
        $("body").append(msgHtml);
        return $(msgHtml).easyModal({
            overlayClose: false,
            closeOnEscape: false
        });
    }

    function timedOut() {
        return { isError: true, message: "Could not connect to webform-signing service!" };
    }

    function showCertSelectionUI(certs, callback) {

        var dialog = $("<div/>").css({
            "display": "none",
            "padding": "15px",
            "border": "7px solid #3aa061",
            "border-radius": "7px",
            "width": "33%",
            "background-color": "white",
            "max-height": "300px",
            "overflow-y": "overlay"

        });

        dialog.append("<h3>Select a certificate</h3>");

        var certsContainer = $("<ul/>").css({
            "margin": 0,
            "padding": 0,
            "list-style": "none"
        });

        for (var i = 0; i < certs.length; i++) {

            var certInfo = certs[i].CertInfo;
            var certId = certs[i].Id;

            var singleCertContainer = $("<li/>").css({
                "border-bottom": "1px solid #eee",
                "padding-top": "7px",
                "cursor": "pointer"
            });

            var cnHtml = $("<div/>").css("font-weight", "bold");
            cnHtml.text(certInfo.SubjectName);

            var otherHtml = $("<p/>").css("margin", "3px 0");
            otherHtml.html(format("Issuer: {0}<br/>Valid From: {1} to {2}", certInfo.Issuer, formateDate(certInfo.ValidFrom), formateDate(certInfo.ExpiresOn)));

            singleCertContainer.append(cnHtml);
            singleCertContainer.append(otherHtml);
            singleCertContainer.hover(function () {
                $(this).css("background-color", "#e4f4f9");
            }, function () {
                $(this).css("background-color", "white");
            });
            singleCertContainer.on('click', (function (x) {
                return function () {
                    callback(x);
                };
            })(certId));

            certsContainer.append(singleCertContainer);
        }

        dialog.append(certsContainer);
        $("body").append(dialog);

        dialog.easyModal();

        return dialog;
    }

    function WebFormSigner(port) {
        this.port = port || 12010;
        this.server = 'http://127.0.0.1';
    };

    WebFormSigner.prototype.getCerts = function getCerts(callback) {
        var certRequest = $.ajax({
            url: format("{0}:{1}/certs/", this.server, this.port),
            async: true,
            crossDomain: true,
            dataType: 'jsonp',
            contentType: 'jsonp',
            timeout: 5000
        });

        certRequest.done(function (result) {
            callback(result);
        }).fail(function () {
            callback(timedOut());
        });
    };

    WebFormSigner.prototype.getCertContent = function getTokenInfoes(certId, callback) {
        var certRequest = $.ajax({
            url: format("{0}:{1}/cerContent/", this.server, this.port),
            data: { certId: certId },
            async: true,
            crossDomain: true,
            dataType: 'jsonp',
            contentType: 'jsonp',
            timeout: 5000
        });

        certRequest.done(function (result) {
            callback(result);
        }).fail(function () {
            callback(timedOut());
        });
    };

    WebFormSigner.prototype.signWithoutUI = function signWithoutUI(msg, certId, pin, callback) {
        var me = this;
        var signRequest = $.ajax({
            url: format("{0}:{1}/sign/", this.server, this.port),
            async: true,
            data: { msg: msg, certId: certId, pin:pin },
            crossDomain: true,
            dataType: 'jsonp',
            contentType: 'jsonp'
        });

        signRequest.done(function (result) {
            callback(result);
        });
    };

    WebFormSigner.prototype.sign = function sign(msg, tokenPin, callback) {

        var me = this;

        var bzModal = getBusyModal("Getting Certificates from token...");

        bzModal.trigger('openModal');

        this.getCerts(function (result) {
            bzModal.trigger('closeModal');
            bzModal.remove();

            if (result.isError) {
                callback(result);
                return;
            }

            var certs = result.data;

            if (certs == null) {
                callback({ isError: true, message: "No certificate found!" });
                return;
            }

            var dialog = showCertSelectionUI(certs, function (certId) {
                dialog.trigger("closeModal");
                dialog.remove();
                if (certId == null) {
                    callback({ isError: true, message: "User Cancelled" });
                } else {
                    bzModal = getBusyModal("Signing data ...");
                    bzModal.trigger('openModal');

                    me.signWithoutUI(msg, certId, tokenPin, function (signResult) {
                        bzModal.trigger('closeModal');
                        callback(signResult);
                    });
                }
            });

            dialog.trigger("openModal");
        });
    };

    WebFormSigner.prototype.getEncryptedMessage = function (msg, callback, hideUI) {

        var showUI = hideUI === undefined || hideUI === false;
        if (showUI) {
            var bzModal = getBusyModal("Encrypting message ...");

            bzModal.trigger('openModal');
        }

        var certRequest = $.ajax({
            url: format("{0}:{1}/encrypt/", this.server, this.port),
            data: { message: msg },
            async: true,
            crossDomain: true,
            dataType: 'jsonp',
            contentType: 'jsonp',
            timeout: 5000
        });

        certRequest.done(function (result) {
            if(showUI)bzModal.trigger('closeModal');
            callback(result);
        }).fail(function () {
            if (showUI) bzModal.trigger('closeModal');
            callback(timedOut());
        });
    };

    WebFormSigner.prototype.selectACertificate = function (callback) {

        var me = this;

        var bzModal = getBusyModal("Getting Certificates from token...");
        bzModal.trigger('openModal');

        this.getCerts(function (result) {
            bzModal.trigger('closeModal');
            bzModal.remove();

            if (result.isError) {
                callback(result);
                return;
            }

            var certs = result.data;

            if (certs == null) {
                callback({ isError: true, message: "No certificate found!" });
                return;
            }

            var dialog = showCertSelectionUI(certs, function (certId) {
                dialog.trigger("closeModal");
                dialog.remove();
                if (certId == null) {
                    callback({ isError: true, message: "User Cancelled" });
                } else {
                    bzModal = getBusyModal("Getting cert content ...");
                    bzModal.trigger('openModal');

                    me.getCertContent(certId, function (certContent) {
                        bzModal.trigger('closeModal');
                        callback(certContent);
                    });
                }
            });

            dialog.trigger("openModal");
        });
    };

    w.WebFormSigner = WebFormSigner;
})(window);

/**
* easyModal.js v1.3.1
* A minimal jQuery modal that works with your CSS.
* Author: Flavius Matis - http://flaviusmatis.github.com/
* URL: https://github.com/flaviusmatis/easyModal.js
*/

/*jslint browser: true*/
/*global jQuery*/

(function ($) {
    "use strict";
    var methods = {
        init: function (options) {

            var defaults = {
                top: 'auto',
                autoOpen: false,
                overlayOpacity: 0.5,
                overlayColor: '#000',
                overlayClose: true,
                overlayParent: 'body',
                closeOnEscape: true,
                closeButtonClass: '.close',
                transitionIn: '',
                transitionOut: '',
                onOpen: false,
                onClose: false,
                zIndex: function () {
                    return (function (value) {
                        return value === -Infinity ? 0 : value + 1;
                    }(Math.max.apply(Math, $.makeArray($('*').map(function () {
                        return $(this).css('z-index');
                    }).filter(function () {
                        return $.isNumeric(this);
                    }).map(function () {
                        return parseInt(this, 10);
                    })))));
                },
                updateZIndexOnOpen: true
            };

            options = $.extend(defaults, options);

            return this.each(function () {

                var o = options,
                    $overlay = $('<div class="lean-overlay"></div>'),
                    $modal = $(this);

                $overlay.css({
                    'display': 'none',
                    'position': 'fixed',
                    // When updateZIndexOnOpen is set to true, we avoid computing the z-index on initialization,
                    // because the value would be replaced when opening the modal.
                    'z-index': (o.updateZIndexOnOpen ? 0 : o.zIndex()),
                    'top': 0,
                    'left': 0,
                    'height': '100%',
                    'width': '100%',
                    'background': o.overlayColor,
                    'opacity': o.overlayOpacity,
                    'overflow': 'auto'
                }).appendTo(o.overlayParent);

                $modal.css({
                    'display': 'none',
                    'position': 'fixed',
                    // When updateZIndexOnOpen is set to true, we avoid computing the z-index on initialization,
                    // because the value would be replaced when opening the modal.
                    'z-index': (o.updateZIndexOnOpen ? 0 : o.zIndex() + 1),
                    'left': 50 + '%',
                    'top': parseInt(o.top, 10) > -1 ? o.top + 'px' : 50 + '%'
                });

                $modal.bind('openModal', function () {
                    var overlayZ = o.updateZIndexOnOpen ? o.zIndex() : parseInt($overlay.css('z-index'), 10),
                        modalZ = overlayZ + 1;

                    if (o.transitionIn !== '' && o.transitionOut !== '') {
                        $modal.removeClass(o.transitionOut).addClass(o.transitionIn);
                    };
                    $modal.css({
                        'display': 'block',
                        'margin-left': -($modal.outerWidth() / 2) + 'px',
                        'margin-top': (parseInt(o.top, 10) > -1 ? 0 : -($modal.outerHeight() / 2)) + 'px',
                        'z-index': modalZ
                    });

                    $overlay.css({ 'z-index': overlayZ, 'display': 'block' });

                    if (o.onOpen && typeof o.onOpen === 'function') {
                        // onOpen callback receives as argument the modal window
                        o.onOpen($modal[0]);
                    }
                });

                $modal.bind('closeModal', function () {
                    if (o.transitionIn !== '' && o.transitionOut !== '') {
                        $modal.removeClass(o.transitionIn).addClass(o.transitionOut);
                        $modal.one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
                            $modal.css('display', 'none');
                            $overlay.css('display', 'none');
                        });
                    }
                    else {
                        $modal.css('display', 'none');
                        $overlay.css('display', 'none');
                    }
                    if (o.onClose && typeof o.onClose === 'function') {
                        // onClose callback receives as argument the modal window
                        o.onClose($modal[0]);
                    }
                });

                // Close on overlay click
                $overlay.click(function () {
                    if (o.overlayClose) {
                        $modal.trigger('closeModal');
                    }
                });

                $(document).keydown(function (e) {
                    // ESCAPE key pressed
                    if (o.closeOnEscape && e.keyCode === 27) {
                        $modal.trigger('closeModal');
                    }
                });

                // Close when button pressed
                $modal.on('click', o.closeButtonClass, function (e) {
                    $modal.trigger('closeModal');
                    e.preventDefault();
                });

                // Automatically open modal if option set
                if (o.autoOpen) {
                    $modal.trigger('openModal');
                }

            });

        }
    };

    $.fn.easyModal = function (method) {

        // Method calling logic
        if (methods[method]) {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        }

        if (typeof method === 'object' || !method) {
            return methods.init.apply(this, arguments);
        }

        $.error('Method ' + method + ' does not exist on jQuery.easyModal');

    };

}(jQuery));
