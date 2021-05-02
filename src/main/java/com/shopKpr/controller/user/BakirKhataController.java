package com.shopKpr.controller.user;

import com.shopKpr.entity.bakirRecord.BakirRecord;
import com.shopKpr.service.bakirKhata.BakirKhataService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_USER')")
public class BakirKhataController {

    private final BakirKhataService bakirKhataService;

    public BakirKhataController( BakirKhataService bakirKhataService ) {
        this.bakirKhataService = bakirKhataService;
    }

    @GetMapping("/getAllBakirRecord")
    public List<BakirRecord> bakirRecordList( @RequestParam("mobileNumber") String mobileNumber)
    {
        return bakirKhataService.getAllBakirRecord(mobileNumber);
    }

    @PostMapping("/addNewRecord")
    public void addNewBakirRecord( @RequestBody BakirRecord bakirRecord)
    {
        bakirKhataService.createNewRecord(bakirRecord);
    }

    @DeleteMapping("/deleteRecord")
    public void deleteRecord( @RequestParam("recordId") Long recordId)
    {
        bakirKhataService.deleteRecord(recordId);
    }

}
