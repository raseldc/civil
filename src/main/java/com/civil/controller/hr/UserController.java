/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.controller.hr;

import com.civil.converter.UserConverter;
import com.civil.detail.UserDetail;
import com.civil.controllers.CommonFunction;
import com.civil.model.Employee;
import com.civil.model.Role;
import com.civil.model.Settings;
import com.civil.model.User;
import com.civil.service.MixService;
import com.civil.service.RoleService;
import com.civil.service.UserService;
import com.civil.util.ChangePassword;
import com.civil.util.JsonResult;
import com.civil.viewClass.UserViewClass;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author rasel
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;
    @Autowired
    CommonFunction commonFunction;
//

//
//    @Autowired
//    private CacheManager cacheManager;
    @Autowired
    private MixService mixService;

    @RequestMapping(value = {"/user/register"}, method = RequestMethod.GET)
    public ModelAndView register() {
        System.out.println("User Reg");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("picture", "../resources/img/bg_login.jpg");
        modelAndView.setViewName("register");

        modelAndView.addObject("model", new User());
        modelAndView.addObject("action", "user/register");
        return modelAndView;
    }

    @RequestMapping(value = {"/user/registerall"}, method = RequestMethod.GET)
    public ModelAndView registerAll() {
        System.out.println("User Reg");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        modelAndView.addObject("action", "user/registrationSumbit");
        return modelAndView;
    }

    @RequestMapping(value = {"/user/register"}, method = RequestMethod.POST)
    public ModelAndView setRegistration(@Valid User user, BindingResult result, Model model, HttpServletRequest request, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();

        String msg = "";

        String password = request.getParameter("tbPassword");

        // byte[] publicKeyByte = Base64.decodeBase64(publickey.getBytes());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPasswordHash(passwordEncoder.encode(password));
        //   user.setPublickey(publicKeyByte);
        User u = userService.getUserByEmail(user.getEmail());
        msg = "Register Sucessfully please wait for admin approve";
        if (u == null) {
            user.setStatus(0);
            Employee e = new Employee();
            e.setCreatedate(new Date());
            mixService.add(e);
            user.setEmployee(e);
            userService.add(user);
            msg = "<div class='successMes'><span>You have registerd sucessfully please wait for admin approval</span></div>";
        } else {
            msg = "<div class='warningMes'><span>Already restered with the following mail , please wait for admin approval</span></div>";
        }
        String email = request.getParameter("tbEmail");
        System.out.println("Get email " + email);
        modelAndView.addObject("picture", "../resources/img/bg_login.jpg");
        modelAndView.setViewName("login");
        modelAndView.addObject("errorMsg", msg);
        return modelAndView;
    }

    @RequestMapping(value = {"/user/userapprove"}, method = RequestMethod.GET)
    public ModelAndView userApprove() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("model", new User());
        modelAndView.addObject("roleList", roleService.getAllRole());

        modelAndView.setViewName("/hr/user/usreApprove");
        modelAndView.addObject("action", "user/usreApprove");
        return modelAndView;
    }

    @RequestMapping(value = "/user/userall")
    @ResponseBody
    public String getAllUser(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {

        String startIndexSt = request.getParameter("start");
        int startPageIndex = startIndexSt != null ? Integer.parseInt(startIndexSt) : 0;
        String recordsPerPageSt = request.getParameter("length");
        int recordsPerPage = recordsPerPageSt != null ? Integer.parseInt(recordsPerPageSt) : Integer.MAX_VALUE;
        String draw = (request.getParameter("draw"));

        List<UserViewClass> userDetailList = userService.getAllUser(startPageIndex, recordsPerPage);
//        List<UserDetail> userDetailList = new ArrayList<UserDetail>();
//        for (User user : userList) {
//            UserDetail ud = new UserDetail();
//            ud = UserConverter.getDetail(user);
//            System.out.println("Uid "+ud.getId());
//            if (user != null) {
//
//                ud.setRole(user.getRole() == null ? "Not Set" : user.getRole().getName());
//            } else {
//                ud.setRole("");
//            }
//
////            try {
////             //   ud.setOwnPhoto_str(commonFunction.getPhotoByEmail(ud.getEmail()));
////            } catch (IOException ex) {
////                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
////            }
//
//            userDetailList.add(ud);
//        }

        //Gson gson = new GsonBuilder().registerTypeAdapter(UserDetail.class, new GenericAdapter()).setPrettyPrinting().create();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String jsonArray = gson.toJson(userDetailList);
        // jsonArray = "{\"Result\":\"OK\",\"Records\":" + jsonArray + ",\"TotalRecordCount\":" + userSize + "}";
        //   jsonArray = "{\"data\":" + jsonArray + "}";

        jsonArray = "{ \"draw\": " + draw + ",\"recordsTotal\": " + userDetailList.size() + ",\"recordsFiltered\": " + userService.getCount() + ",\"data\":" + jsonArray + "}";

        System.out.println("Ajax " + jsonArray);
        return jsonArray;
    }

    @RequestMapping(value = "/user/userapprove", method = RequestMethod.POST)
    @ResponseBody
    public String setUserApprove(@RequestParam(value = "uId", required = false) String uId, @RequestParam(value = "rId",
            required = false) String rId,
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession httpSession) {

        int id = Integer.parseInt(uId);
        User user_forAssignRole = userService.get(id);

        user_forAssignRole.setStatus(1);

        userService.update(user_forAssignRole);
        int roleId = Integer.parseInt(rId);

        Role r = roleService.get(roleId);
        user_forAssignRole.setRole(r);

        JsonResult jr = new JsonResult(false, "User Role Update Successfully");
        try {
            userService.update(user_forAssignRole);
        } catch (Exception ex) {
            jr.setIsError(true);
            jr.setErrorMsg(ex.getMessage());
            ex.printStackTrace();
        }
        return jr.toJsonString();
    }

    @RequestMapping(value = "/user/userremove", method = RequestMethod.POST)
    @ResponseBody
    public String setUserRemove(@RequestParam(value = "uId", required = false) String uId, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {

        int id = Integer.parseInt(uId);
        User u = userService.get(id);

        userService.update(u);
        return "{[ok]}";
    }

    @RequestMapping(value = {"/user/userrole"}, method = RequestMethod.GET)
    public ModelAndView userRole() {
        System.out.println("User Reg");
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("user_role");

        modelAndView.addObject("userList", userService.getAll());
        modelAndView.addObject("roleList", roleService.getAll());

        modelAndView.addObject("model", new User());
        modelAndView.addObject("action", "user/userRole");
        return modelAndView;
    }

    // <editor-fold defaultstate="collapsed" desc="Usre Detail">
    @RequestMapping(value = {"/user/userdetail"}, method = RequestMethod.GET)
    public ModelAndView userDetail() {
        ModelAndView modelAndView = new ModelAndView();

        org.springframework.security.core.userdetails.User user;
        try {
            user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String email = user.getUsername();
            User userInformation = userService.getUserByEmail(email);

            modelAndView.addObject("model", userInformation);
            modelAndView.addObject("action", "user/userdetail");

            //List<Certification> certList = mixService.getAll(new Certification());
            //  modelAndView.addObject("certList", certList);
            userInformation.setRole(null);
            if (userInformation.getEmployee() != null) {
                userInformation.getEmployee().setUsers(null);
            }

        } catch (Exception e) {
            System.out.println("Ex" + e.getStackTrace());
            modelAndView.setViewName("login");
            return modelAndView;
        }

        modelAndView.setViewName("/hr/user/userDetail");
        return modelAndView;

    }

    @RequestMapping(value = {"/user/userdetail"}, method = RequestMethod.POST)
    public ModelAndView userDetailUpdate(@Valid User user, BindingResult result, Model model, HttpServletRequest request, HttpSession session) {

        org.springframework.security.core.userdetails.User user_auth;
        user_auth = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = user_auth.getUsername();
        User u = userService.getUserByEmail(email);

        u.setFullName(user.getFullName());

        if (u.getEmployee() == null) {
            Employee e = new Employee();
            u.setEmployee(e);
        }
        u.getEmployee().setFatherName(user.getEmployee().getFatherName());
        u.getEmployee().setMotherName(user.getEmployee().getMotherName());
        u.getEmployee().setMobileNumber(user.getEmployee().getMotherName());
        u.getEmployee().setNid(user.getEmployee().getNid());
        u.getEmployee().setPost(user.getEmployee().getPost());

        //education
        u.getEmployee().setDegree(user.getEmployee().getDegree());
        u.getEmployee().setInstitution(user.getEmployee().getInstitution());
        u.getEmployee().setYear(user.getEmployee().getYear());
        u.getEmployee().setSubject(user.getEmployee().getSubject());

        userService.update(u);

        ModelAndView modelAndView = new ModelAndView();

        //List<Certification> certList = mixService.getAll(new Certification());
        //modelAndView.addObject("certList", certList);
        modelAndView.addObject("model", user);
        modelAndView.addObject("action", "user/userdetail");
        modelAndView.setViewName("/hr/user/userDetail");
        return modelAndView;
    }

    @RequestMapping(value = "/user/uploadpicture", method = RequestMethod.POST)
    public @ResponseBody
    String uploadPicture(MultipartHttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {

        //0. notice, we have used MultipartHttpServletRequest
        //1. get the files from the request object
        try {
            Iterator<String> itr = request.getFileNames();
            User user = (User) request.getSession().getAttribute("User");
            MultipartFile mpf = request.getFile(itr.next());
            mpf.getOriginalFilename().endsWith(".");
            System.out.println(mpf.getOriginalFilename() + " uploaded!");
            byte[] bytes = mpf.getBytes();

            String name = mpf.getOriginalFilename().toString();
            String suffix = name.split("\\.")[1].toLowerCase();
            if (!suffix.equals("png") && !suffix.equals("jpg")) {
                return "Please Upload only jpg file";
            }
            mpf.getSize();
            // Creating the directory to store file

            String rootPath = System.getProperty("catalina.home");

            Settings s = mixService.getSettingsByKey("userProfilePicLocation");
            String ralPath = "";
            if (s != null) {
                ralPath = s.getValue();//ApplicationSettings.userProfilePicLocation;
            } else {
                return null;
            }
            File dir = new File(ralPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            // Create the file on server
            File serverFile = new File(ralPath + user.getEmail() + ".jpg");

            String filePath = serverFile.getPath();

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
            System.out.println("Bytes array " + bytes.toString());
            String base64String = Base64.encodeBase64String(bytes);

            // File pf = new File(ApplicationSettings.userProfilePicLocation + "" + user.getEmail() + ".jpg");
            BufferedImage bImage = ImageIO.read(serverFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos);
            byte[] data = bos.toByteArray();
            base64String = Base64.encodeBase64String(data);
            base64String = "data:image/png;base64," + base64String;
            HttpSession session = request.getSession();
            session.setAttribute("base64_pf", base64String);
            return base64String;

        } catch (Exception e) {
            JsonResult jr = new JsonResult(true, e.getMessage());
            return jr.toJsonString();
        }

        //2. send it back to the client as <img> that calls get method
        //we are using getTimeInMillis to avoid server cached image 
        //return "Upload Complete";
    }

    @RequestMapping(value = "/user/getpicture", method = RequestMethod.POST)
    public String getUserPicture(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");

        try {
            String photoByEmail = commonFunction.getPhotoByEmail(email);
            return photoByEmail;
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Change Password">
    @RequestMapping(value = {"/user/changepassword"}, method = RequestMethod.GET)
    public ModelAndView ChangePassword() {
        System.out.println("Change Password");
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("actionPath", "/user/changepassword");
        modelAndView.addObject("change", new ChangePassword());
        modelAndView.addObject("isOk", "0");
        modelAndView.setViewName("changePassword");

        return modelAndView;
    }

    @RequestMapping(value = {"/user/changepassword"}, method = RequestMethod.POST)
    public ModelAndView ChangePasswordSubmit(@Valid ChangePassword changePassword, BindingResult result, Model model, HttpServletRequest request
    ) {
        String old = changePassword.getOld();
        String newpassword = changePassword.getNewpassword();
        String new_con = request.getParameter("tbNewPasswordConfirm");

        System.out.println("old................" + changePassword.getOld());
        System.out.println("new................" + changePassword.getNewpassword());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("change", new ChangePassword());
        modelAndView.addObject("isOk", "0");
        org.springframework.security.core.userdetails.User user;
        try {
            user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {

            modelAndView = new ModelAndView();
            //   modelandView.addObject("title", "Spring Security + Hibernate Example");
            // modelandView.addObject("message", "This is default page!");
            modelAndView.setViewName("login");

            return modelAndView;
        }

        if (!newpassword.equals(new_con)) {
            modelAndView.addObject("message", "Password Missmatch");
            modelAndView.setViewName("changePassword");
            return modelAndView;
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User ur = userService.getUserByEmail(user.getUsername());

        String pass = ur.getPasswordHash();
        if (!passwordEncoder.matches(old, pass)) {
            modelAndView.addObject("message", "Password Not Match with old password");
            modelAndView.setViewName("changePassword");
            return modelAndView;
        }

        ur.setPasswordHash(passwordEncoder.encode(newpassword));

        userService.update(ur);
        modelAndView.addObject("message", "Password Change SucessFully");
        modelAndView.setViewName("changePassword");
        modelAndView.addObject("isOk", "1");
        return modelAndView;

    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Education Certificate">
    @RequestMapping(value = "/user/getcertificatelist")
    @ResponseBody
    public String getAllCertification(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {

        String startIndexSt = request.getParameter("start");
        int startPageIndex = startIndexSt != null ? Integer.parseInt(startIndexSt) : 0;
        String recordsPerPageSt = request.getParameter("length");
        int recordsPerPage = recordsPerPageSt != null ? Integer.parseInt(recordsPerPageSt) : Integer.MAX_VALUE;
        int draw = Integer.parseInt(request.getParameter("draw"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userByEmail = userService.getUserByEmail(authentication.getName());

//        List<EmployeeCertificationsDetail> employeeCertifications = mixService.getAllCertificationByUserId(userByEmail.getEmployee().getId());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonArray = gson.toJson("");
        // jsonArray = "{\"Result\":\"OK\",\"Records\":" + jsonArray + ",\"TotalRecordCount\":" + userSize + "}";
        //   jsonArray = "{\"data\":" + jsonArray + "}";

        // jsonArray = "{ \"draw\": " + draw + ",\"recordsTotal\": " + employeeCertifications.size() + ",\"recordsFiltered\": " + employeeCertifications.size() + ",\"data\":" + jsonArray + "}";
        System.out.println("Ajax " + jsonArray);
        return jsonArray;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Usre Add">
    @RequestMapping(value = {"/user/useradd"}, method = RequestMethod.GET)
    public ModelAndView useradd() {
        System.out.println("User Reg");
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("hr/user/useradd");

        modelAndView.addObject("roleList", roleService.getAll());

        modelAndView.addObject("model", new User());
        modelAndView.addObject("action", "user/useradd");
        return modelAndView;
    }

    @RequestMapping(value = {"/user/useradd"}, method = RequestMethod.POST)
    @ResponseBody
    public String useradd(@RequestBody UserDetail userDetail, HttpServletRequest request) {
        JsonResult jr = new JsonResult(false, "User Add sucessfully");
        if (userDetail.getFullName().isEmpty()) {
            jr = new JsonResult(true, "Please Give Full Name");
            return jr.toJsonString();
        } else if (userDetail.getEmail().isEmpty() || !isValidEmail(userDetail.getEmail())) {

            jr = new JsonResult(true, "Please Give Valid Email");
            return jr.toJsonString();
        } else if (0 == userDetail.getRoleId()) {

            jr = new JsonResult(true, "Please Select a Role");
            return jr.toJsonString();
        } else if (userDetail.getReporterTypeId() == 0) {

            jr = new JsonResult(true, "Please Select Reporter Type");
            return jr.toJsonString();
        } else if (userDetail.getStatus() != 0 && userDetail.getStatus() != 1) {

            jr = new JsonResult(true, "Please Select Status");
            return jr.toJsonString();
        }

        //validation 
        User user_db = userService.getUserByEmail(userDetail.getEmail());
        if (userDetail.getId() == 0 && user_db != null) {
            jr = new JsonResult(true, "User already Exist");
            return jr.toJsonString();
        }
        ///

        User user = new User();
        user = UserConverter.getEntity(userDetail);
        Role role = new Role();
        role.setId(userDetail.getRoleId());

        user.setRole(role);

        try {
            if (userDetail.getPasswordReset() == 1) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                user.setPasswordHash(passwordEncoder.encode("123456"));
            }
            if (user.getId() == 0) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                user.setPasswordHash(passwordEncoder.encode("123456"));
                userService.add(user);

            } else {
                user_db.setFullName(user.getFullName());
                user_db.setEmail(user.getEmail());
                user_db.setRole(user.getRole());

                user_db.setStatus(user.getStatus());
                userService.update(user_db);
            }

        } catch (Exception ex) {
            jr = new JsonResult(true, "User add/update failed due to server error , please contact with dev.");
        }
        return jr.toJsonString();

    }

    static boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    // </editor-fold>
}
