/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.controllers;

import com.civil.converter.ItemConverter;
import com.civil.detail.ItemDetail;
import com.civil.model.Item;
import com.civil.service.ItemService;
import com.civil.util.JsonResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Rasel
 */
@Controller
@RequestMapping(value = {"/item/"})
public class ItemController {

    @Autowired
    ItemService itemService;

    @RequestMapping(value = {"/itemadd"}, method = RequestMethod.GET)
    public ModelAndView getItemView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/item/itemAdd");
        return modelAndView;
    }

    @RequestMapping(value = "/itemadd", method = RequestMethod.POST)
    @ResponseBody
    public String itemAdd(@RequestBody ItemDetail itemDetail, HttpServletRequest request) {

        
        
        Item item = new Item();
        item = ItemConverter.getEntity(itemDetail);

        if (item.getId() == null) {
            itemService.add(item);
        } else {
            itemService.update(item);
        }

        JsonResult jr = new JsonResult(false, "ok");

        return jr.toJsonString();

    }

    //for dataDatable
    @RequestMapping(value = "/getitemlist")
    @ResponseBody
    public String getItemList(HttpServletRequest request) {
        String startIndexSt = request.getParameter("jtStartIndex");
        int startPageIndex = startIndexSt != null ? Integer.parseInt(startIndexSt) : 0;
        String recordsPerPageSt = request.getParameter("jtPageSize");
        int recordsPerPage = recordsPerPageSt != null ? Integer.parseInt(recordsPerPageSt) : Integer.MAX_VALUE;
        //  int draw = Integer.parseInt(request.getParameter("draw"));

        System.out.println("Paichi");

        List<ItemDetail> itemDetails = itemService.getItemDetailList(startPageIndex, recordsPerPage);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonArray = gson.toJson(itemDetails);
        int count = itemDetails.size() > 0 ? itemDetails.get(0).getCount() : 0;
        jsonArray = "{\"recordsTotal\": " + itemDetails.size() + ",\"recordsFiltered\": " + count + ",\"data\":" + jsonArray + "}";

        return jsonArray;

    }

    @RequestMapping(value = "/get-all-item")
    @ResponseBody
    public String getAllItem(HttpServletRequest request) {
        String startIndexSt = request.getParameter("jtStartIndex");
        int startPageIndex = startIndexSt != null ? Integer.parseInt(startIndexSt) : 0;
        String recordsPerPageSt = request.getParameter("jtPageSize");
        int recordsPerPage = recordsPerPageSt != null ? Integer.parseInt(recordsPerPageSt) : Integer.MAX_VALUE;
        //  int draw = Integer.parseInt(request.getParameter("draw"));

        System.out.println("Paichi");

        List<ItemDetail> itemDetails = itemService.getItemDetailList(startPageIndex, recordsPerPage);

        JsonResult jr = new JsonResult(false, "ok");

        jr.setReturnObject(itemDetails);
        return jr.toJsonString();

    }

    @RequestMapping(value = {"/fileupload"}, method = RequestMethod.GET)
    public ModelAndView itemFileUpload() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/item/fileUpload");
        modelAndView.addObject("actionPath", "/item/uploadMultipleFile");
        return modelAndView;
    }

    @RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
    public String uploadMultipleFileHandler(
            @RequestParam("file") MultipartFile[] files, @RequestParam("cmpId") String cmpId) {

        JsonResult jr = new JsonResult(false, "ok");
        if (files[0].isEmpty()) {
            jr.setErrorMsg("File is empty");
            jr.setIsError(true);
            return jr.toJsonString();
        }

        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            String name = "";//names[i];
            try {
                byte[] bytes = file.getBytes();

                name = file.getOriginalFilename().toString();
                // Creating the directory to store file
                String rootPath = "G:\\Civil";//System.getProperty("catalina.home");

                File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                //dir = new File("/Users/shamiulislam/nest_inafi/");
                // dir = new File("D:/");
                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + name);

                String filePath = serverFile.getPath();

                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                List<ItemDetail> itemDetails = readExcle_temp(filePath);

                itemService.insertItemBatch(itemDetails);
//                for (ItemDetail itemDetail : itemDetails) {
//                    Item item = ItemConverter.getEntity(itemDetail);
//                    itemService.add(item);
//                }

            } catch (Exception e) {

                return jr.toJsonString();//"You failed to upload " + name + " => " + e.getMessage();

            }
        }
        return jr.toJsonString();

    }

    private List<ItemDetail> readExcle_temp(String filePath) {

        int row_count = 0;
        int col_count = 0;
        try {

            FileInputStream file = new FileInputStream(new File(filePath));

            List<ItemDetail> itemDetails = new ArrayList<ItemDetail>();

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.hasNext();
            int i = 0;

            while (rowIterator.hasNext()) {

                col_count = 0;
                row_count++;
                Row row = rowIterator.next();
                if (i < 1) {
                    i++;
                    continue;
                }
                String val = row.getCell(0) == null ? "" : row.getCell(0).toString();
//                val.matches("\\\\d+(\\\\.\\\\d+)*");
//                String pattern = "^[0-9]{1,2}([.][0-9]{1,2})?$";
//                Pattern regexPattern = Pattern.compile(pattern);
//                Matcher matcher = regexPattern.matcher(val);
//                boolean isMatch = matcher.find();
                if (val != "" && !val.matches("\\d+(\\.\\d+)*")) {
                    continue;
                }
                if (val.startsWith("0")) {
                    val = val.substring(1, val.length());
                }

                Iterator<Cell> cellIterator = row.cellIterator();

                ItemDetail itemDetail = new ItemDetail();
//                if (row.getCell(0) == null) {
//                    continue;
//                }

                itemDetail.setCode(val);

                itemDetail.setDescription(row.getCell(1) != null ? row.getCell(1).toString() : "");
                itemDetail.setUnit(row.getCell(2) != null ? row.getCell(2).toString() : "");
                if (row.getCell(3) != null) {
                    if (!"".equals(row.getCell(3).toString())) {
                        try {
                            float price = Float.parseFloat(row.getCell(3).toString());
                            itemDetail.setPrice(price);
                        } catch (Exception ex) {
                            itemDetail.setPrice(Float.parseFloat("0"));
                        }

                    }
                }
                itemDetail.setParent_code(row.getCell(4) != null ? row.getCell(4).toString() : "");

                if ("".equals(itemDetail.getCode()) && "".equals(itemDetail.getParent_code())) {
                    continue;
                }

                if (!"".equals(itemDetail.getParent_code())) {

                    itemDetail.setParent_code(itemDetail.getParent_code().toLowerCase().trim().replaceAll("parent", ""));
                    itemDetail.setParent_code(itemDetail.getParent_code().toLowerCase().trim().replaceAll(" ", ""));
                }

                itemDetail.setCode("em-" + val);

                itemDetail.setParent_code("em-" + itemDetail.getParent_code());

                itemDetails.add(itemDetail);

            }

            file.close();

            // emp = chcek_exitence_emp(emp);
            return itemDetails;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

}
