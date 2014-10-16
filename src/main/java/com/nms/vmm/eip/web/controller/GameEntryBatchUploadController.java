/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.vmm.eip.web.controller;

import com.nms.vmm.eip.ejb.GameEntryFacade;
import com.nms.vmm.eip.entity.Flatform;
import com.nms.vmm.eip.entity.GameCategory;
import com.nms.vmm.eip.entity.Game;
import com.nms.vmm.eip.entity.User;
import com.nms.vmm.eip.web.util.MessageUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Cuong
 */
@Named
@SessionScoped
public class GameEntryBatchUploadController implements Serializable {

    private static final long serialVersionUID = 2949111781475936685L;
    @EJB
    private GameEntryFacade facade;
    private GameCategory category;
    private Part fileUpload;
    @Inject
    private UserEntryController userEntryController;
    @Inject
    private GameEntryController gameEntryController;

    public GameCategory getCategory() {
        return category;
    }

    public void setCategory(GameCategory category) {
        this.category = category;
    }

    public Part getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(Part fileUpload) {
        this.fileUpload = fileUpload;
    }

    public String upload() {

        Iterator<Row> rowIter = null;

        if (fileUpload != null) {
            String fileName = fileUpload.getSubmittedFileName();

            if (fileName.endsWith(".xls") || fileName.endsWith(".xlsx")) {
                if (fileName.endsWith(".xlsx")) {
                    try {
                        XSSFWorkbook workbook = new XSSFWorkbook(fileUpload.getInputStream());
                        XSSFSheet sheet = workbook.getSheetAt(0);
                        rowIter = sheet.iterator();
                    } catch (IOException e) {
                        MessageUtil.addGlobalErrorMessage();
                    }
                }

                if (fileName.endsWith(".xls")) {
                    try {
                        HSSFWorkbook workbook = new HSSFWorkbook(fileUpload.getInputStream());
                        HSSFSheet sheet = workbook.getSheetAt(0);
                        rowIter = sheet.iterator();
                    } catch (IOException e) {
                        MessageUtil.addGlobalErrorMessage();
                    }
                }
            }
        }

        if (rowIter != null) {
            List<Game> games = getGameEntries(rowIter);

            try {
                User userEntry = userEntryController.getUserFromRequest();
                String cpCode = null;
                if (userEntry != null) {
                    cpCode = userEntry.getCode();
                }
                facade.create(games, cpCode);
                MessageUtil.addGlobalSuccessMessage();
            } catch (Exception e) {
                MessageUtil.addGlobalPersistenceErrorMessage();
                return null;
            }
        }
        
        // reset game list
        gameEntryController.setItems(null);
        gameEntryController.setPaginationHelper(null);

        return "list?faces-redirect=true";
    }

    private List<Game> getGameEntries(Iterator<Row> rowIterater) {

        List<Game> gameEntries = new ArrayList<>();

        // next header row
        if (rowIterater.hasNext()) {
            rowIterater.next();
        }

        // process other row.
        while (rowIterater.hasNext()) {
            Game gameEntry = new Game();
            gameEntry.setCategory(category);

            Row row = rowIterater.next();

            Iterator<Cell> cellIter = row.cellIterator();

            // title
            Cell titleCell = cellIter.next();
            gameEntry.setTitle(getStringCellValue(titleCell));

            // code
            Cell codeCell = cellIter.next();
            gameEntry.setCode(getStringCellValue(codeCell));

            // price
            Cell priceCell = cellIter.next();
            gameEntry.setPrice(priceCell.getNumericCellValue());

            // description
            Cell descriptionCell = cellIter.next();
            gameEntry.setDescription(getStringCellValue(descriptionCell));

            // specification
            Cell specificationCell = cellIter.next();
            gameEntry.setSpecification(getStringCellValue(specificationCell));

            // thumbnailUrl
            Cell thumbnailUrlCell = cellIter.next();
            gameEntry.setThumbnailUrl(getStringCellValue(thumbnailUrlCell));

            // screenshortUrls 
            Cell scrShortUrlsCell = cellIter.next();
            String[] scrShortUrls = getStringCellValue(scrShortUrlsCell).split(",");
            gameEntry.setScreenShorts(Arrays.asList(scrShortUrls));

            // flatform
            Cell flatformsCel = cellIter.next();
            if (getStringCellValue(flatformsCel) != null) {
                String[] flatformStrs = getStringCellValue(flatformsCel).split(",");
                if (flatformStrs != null && flatformStrs.length > 0) {
                    Flatform[] flatforms = new Flatform[flatformStrs.length];
                    for (int i = 0; i < flatformStrs.length; i++) {
                        String flatformStr = flatformStrs[i];
                        int intValue = 10;
                        try {
                            intValue = Integer.valueOf(flatformStr);
                        } catch (NumberFormatException e) {
                        }

                        if (intValue != 10) {
                            flatforms[i] = Flatform.values()[intValue - 1];
                        }
                    }
                    gameEntry.setFlatforms(Arrays.asList(flatforms));
                }
            }

            // deviceSupport
            Cell deviceSupportCell = cellIter.next();
            gameEntry.setDevicesSupport(getStringCellValue(deviceSupportCell));

            // tags processing.
            gameEntries.add(gameEntry);
        }

        return gameEntries;
    }

    private String getStringCellValue(Cell cell) {
        String value = null;
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BLANK:
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_ERROR:
                break;
            case Cell.CELL_TYPE_FORMULA:
                break;
            case Cell.CELL_TYPE_STRING:
                value = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                double doubleValue = cell.getNumericCellValue();
                if (doubleValue == (int) doubleValue) {
                    value = String.format("%d", (int) doubleValue);
                } else {
                    value = String.format("%s", doubleValue);
                }
                
                break;
        }

        return value;
    }
}
