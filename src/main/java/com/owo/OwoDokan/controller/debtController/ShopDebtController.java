package com.owo.OwoDokan.controller.debtController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import com.itextpdf.text.*;
import com.owo.OwoDokan.ModelClass.debt.DebtDashBoardResponse;
import com.owo.OwoDokan.entity.shops.shopsData.UserDebts;
import com.owo.OwoDokan.entity.shops.shopsData.User_debt_details;
import com.owo.OwoDokan.service.shopKeeper.Debt.ShopUserDebtService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@RestController
public class ShopDebtController {

    private final ShopUserDebtService shopUserDebtService;

    public ShopDebtController( ShopUserDebtService shopUserDebtService) {
        this.shopUserDebtService = shopUserDebtService;
    }

    @PostMapping("/addUserDebt") //this is the first time when shop keeper will add user to debt list
    public ResponseEntity<String> addUserDebt(@RequestBody UserDebts userDebts, @RequestParam(name = "shop_mobile_number") String shop_mobile_number)
    {
        return shopUserDebtService.addDebt(userDebts, shop_mobile_number);
    }

    @PostMapping("/addPaidAmountForAnUser")
    public ResponseEntity<String> addPaidAmountForAnUser(@RequestParam("userId") Long userId, @RequestParam("paidAmount") Double userPaidAmount)
    {
        return shopUserDebtService.addPaidAmount(userId, userPaidAmount);
    }

    @GetMapping("/getDebtDashBoardEntries")
    public DebtDashBoardResponse getDebtDashBoardEntries(@RequestParam("mobileNumber") String mobileNumber)
    {
        return shopUserDebtService.getDebtDashBoardEntries(mobileNumber);
    }

    @GetMapping("/getUserDebtLists")
    public ResponseEntity getUserDebtLists(@RequestParam(name = "page") int page, @RequestParam(name = "shop_mobile_number") String shop_mobile_number)
    {
        return shopUserDebtService.getAllDebts(page, shop_mobile_number);
    }

    @PostMapping("/addAdebtDetails")
    public ResponseEntity<String> addAdebtDetails(@RequestBody User_debt_details user_debt_details,
                                                  @RequestParam(name = "user_id") Long user_id)
    {
        return shopUserDebtService.addDebtDetails(user_debt_details, user_id);
    }

    @DeleteMapping("/deleteAdebtDetails")
    public ResponseEntity<String> deleteAdebtDetails(@RequestParam(name = "id_of_debt_details") long id_of_debt_details, @RequestParam(name = "user_id") long user_id)
    {
        return shopUserDebtService.deleteAdebtDetails(id_of_debt_details, user_id);
    }

    @PutMapping("/updateAdebtDetails") //Updating a customer's debt_details
    public ResponseEntity updateAdebtDetails(@RequestBody User_debt_details user_debt_details, @RequestParam(name = "user_id") long user_id)
    {
        return shopUserDebtService.updateAdebtDetails(user_debt_details, user_id);
    }

    @DeleteMapping("/clearAllDebtDetails") //this is for clearing a customer all debt details
    public ResponseEntity clearAllDebtDetails(@RequestParam(name = "user_id") Long user_id)
    {
        return shopUserDebtService.clearAllDebtDetails(user_id);
    }


    @GetMapping("/getUserSpecificDebtDetails") //This method is for getting debt details for an user
    public ResponseEntity getAllDebtDetails(@RequestParam(name = "user_id") Long user_id)
    {
        return shopUserDebtService.getAllDebtDetails(user_id);
    }

    @GetMapping("/getADebtListForAUser")
    public ResponseEntity getADebtListForUser(@RequestParam(name = "user_id") Long user_id)
    {
        return shopUserDebtService.getDebtDetailsForACustomer(user_id);
    }

    @GetMapping("/getAllDebtDetailsReport") //This method is for getting pdf report of the debt for a user
    public ResponseEntity<Resource> generateExcelReport(@RequestParam(name = "user_id") Long user_id) throws DocumentException
    {

        List<User_debt_details> user_debt_details = shopUserDebtService.getAllDebtDetailsViaList(user_id);

        String customer_name = shopUserDebtService.getCustomerName(user_id);

        Document document = new Document(PageSize.A4, 25, 25, 25, 25);

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, os);

        document.open();

        Paragraph title = new Paragraph(customer_name+" Loan details",
                FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD, new BaseColor(255, 0, 0)));

        document.add(title);

        PdfPTable table = new PdfPTable(4);
        table.setSpacingBefore(25);
        table.setSpacingAfter(25);

        PdfPCell c1 = new PdfPCell(new Phrase("Number"));
        table.addCell(c1);

        PdfPCell c2 = new PdfPCell(new Phrase("Description"));
        table.addCell(c2);

        PdfPCell c3 = new PdfPCell(new Phrase("Date"));
        table.addCell(c3);

        PdfPCell c4 = new PdfPCell(new Phrase("TK"));
        table.addCell(c4);

        int i;

        int length = user_debt_details.size();

        double totalLoan =0.0;

        for(i=0; i<length; i++)
        {
            table.addCell(String.valueOf(i+1));
            table.addCell(user_debt_details.get(i).getDescription());
            table.addCell(user_debt_details.get(i).getDate());
            table.addCell(String.valueOf(user_debt_details.get(i).getTaka()));

            totalLoan += user_debt_details.get(i).getTaka();
        }

        table.addCell(createCell("Total Cost", 1, 3, Element.ALIGN_RIGHT));
        table.addCell(createCell(String.valueOf(totalLoan), 1, 1, Element.ALIGN_LEFT));

        Double paidAmount = shopUserDebtService.getPaidAmount(user_id);

        table.addCell(createCell("Total Paid", 1, 3, Element.ALIGN_RIGHT));
        table.addCell(createCell(String.valueOf(paidAmount), 1, 1, Element.ALIGN_LEFT));

        table.addCell(createCell("Total Due", 1, 3, Element.ALIGN_RIGHT));
        table.addCell(createCell(String.valueOf(totalLoan - paidAmount), 1, 1, Element.ALIGN_LEFT));


        document.add(table);

        document.close();

        ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=DebtDetails.pdf");

        return new ResponseEntity<>(new InputStreamResource(is), headers, HttpStatus.OK);
    }

    public PdfPCell createCell(String content, float borderWidth, int colspan, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(content));
        cell.setBorderWidth(borderWidth);
        cell.setColspan(colspan);
        cell.setHorizontalAlignment(alignment);
        return cell;
    }

}
