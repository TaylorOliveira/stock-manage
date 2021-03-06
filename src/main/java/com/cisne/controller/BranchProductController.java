package com.cisne.controller;

import com.cisne.payload.branchProduct.BranchProductRequest;
import com.cisne.payload.branchProduct.BranchProductResponse;
import com.cisne.service.BranchProductService;
import com.cisne.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class BranchProductController {

    private final BranchProductService branchProductService;

    @Autowired
    public BranchProductController(BranchProductService branchProductService) {
        this.branchProductService = branchProductService;
    }

    @GetMapping("/branchProduct/{id}")
    public BranchProductResponse getBranchProductById(@PathVariable("id") Long id) {
        return branchProductService.getBranchProductById(id);
    }

    @PostMapping("/branchProduct")
    public ResponseEntity<?> createBranchProduct(@Validated @RequestBody BranchProductRequest branchProductRequest) {
        BranchProductResponse branchProductResponse = branchProductService.createBranchProduct(branchProductRequest);
        if(Objects.isNull(branchProductResponse)){
            return Utils.badRequest(false, "Error saving!");
        }
        return Utils.created(true, "Transfer successfully created.");
    }

    @PutMapping("/branchProduct")
    public ResponseEntity<?> updateBranchProduct(@Valid @RequestBody BranchProductRequest branchProductRequest){
        branchProductService.updateBranchProduct(branchProductRequest);
        return Utils.created(true, "Transfer successfully created.");
    }

    @DeleteMapping("/branchProduct/{id}")
    public ResponseEntity<?> deleteBranchProduct(@PathVariable("id") Long id) {
        branchProductService.deleteBranchProduct(id);
        return Utils.deleted(true, "Transfer deleted on success.");
    }

    @GetMapping("/listBranchProducts")
    public List<BranchProductResponse> getAllBranchProducts() {
        return branchProductService.getAllBranchProducts();
    }
}
