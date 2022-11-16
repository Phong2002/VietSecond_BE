package com.phenikaa.vietsecond.Presentation_Layer;


import com.phenikaa.vietsecond.Business_Logic_Layer.VietnameseProvinces.DistrictService;
import com.phenikaa.vietsecond.Business_Logic_Layer.VietnameseProvinces.WardService;
import com.phenikaa.vietsecond.Data_Access_Layer.VietnameseProvincesRepository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/address")
@CrossOrigin("*")
public class VietnameseProvincesController {
    @Autowired
    DistrictService districtService;

    @Autowired
    ProvinceRepository provinceRepository;

    @Autowired
    WardService wardService;

    @GetMapping("district/{id}")
    public ResponseEntity<?> getAllDistrictByProvince(@PathVariable(name = "id") String id){
        return new ResponseEntity<>(districtService.findByProvinceId(id), HttpStatus.OK);
    }

    @GetMapping("province")
    public ResponseEntity<?> getAllProvince(){
        return new ResponseEntity<>(provinceRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("ward/{id}")
    public ResponseEntity<?> getAllWard(@PathVariable(name = "id") String id){
        return new ResponseEntity<>(wardService.findByDistrictId(id), HttpStatus.OK);
    }
}
