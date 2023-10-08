package com.mitocode.fullstack.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.mitocode.fullstack.dto.*;
import com.mitocode.fullstack.model.Consult;
import com.mitocode.fullstack.model.Exam;
import com.mitocode.fullstack.model.MediaFile;
import com.mitocode.fullstack.service.IConsultService;
import com.mitocode.fullstack.service.IMediaFileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.cloudinary.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/consults")
@RequiredArgsConstructor
public class ConsultController {
    private final IConsultService service;
    private final IMediaFileService mfService;

    private final Cloudinary cloudinary;
    @Qualifier("consultMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ConsultDTO>> readAll() throws Exception{

        List<ConsultDTO> list = service.readAll().stream().map(e->mapper.map(e,ConsultDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ConsultDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Consult consult = service.readById(id);
        return new ResponseEntity<>(mapper.map(consult,ConsultDTO.class), HttpStatus.OK);
    }
    /*
    @PostMapping
    public ResponseEntity<ConsultDTO> create(@Valid @RequestBody ConsultDTO dto) throws Exception{
        Consult consult1 = service.create(mapper.map(dto, Consult.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(consult1.getIdConsult()).toUri();
        //return new ResponseEntity<>(consult1,HttpStatus.CREATED);
        return ResponseEntity.created(location).build();
    }*/
    @PostMapping
    public ResponseEntity<ConsultDTO> create(@Valid @RequestBody ConsultListExamDTO dto) throws Exception{
        Consult consult = mapper.map(dto.getConsultDTO(), Consult.class) ;
        List<Exam> list = dto.getListExam().stream().map(e-> mapper.map(e, Exam.class)).collect(Collectors.toList());
        Consult obj = service.saveTransactional(consult, list);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdConsult()).toUri();
        //return new ResponseEntity<>(consult1,HttpStatus.CREATED);
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultDTO> update(@PathVariable("id") Integer id,@Valid @RequestBody ConsultDTO dto) throws Exception{
        dto.setIdConsult(id);
        Consult obj1 = service.update(id, mapper.map(dto, Consult.class));
        return new ResponseEntity<>(mapper.map(obj1,ConsultDTO.class) , HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception{
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/search/others")
    public ResponseEntity<List<ConsultDTO>> search(@RequestBody FilterConsultDTO filterConsultDTO){
        List<ConsultDTO> list = service.search(filterConsultDTO.getDni(), filterConsultDTO.getFullname() ).stream().map(e-> mapper.map(e, ConsultDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/search/dates")
    public ResponseEntity<List<ConsultDTO>> searchByDates(
            @RequestParam(value = "date1", defaultValue = "2023-07-15") String date1,
            @RequestParam("date2") String date2
    ){
        List<ConsultDTO> list = service.searchByDates(LocalDateTime.parse(date1), LocalDateTime.parse(date2)).stream().map(e->mapper.map(e, ConsultDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/callProcedureNative")
    public ResponseEntity<List<ConsultProcDTO>> callProcedureOrFunctionNative(){
        List<ConsultProcDTO> consults = service.callProcedureOrFunctionNative();
        return new ResponseEntity<>(consults, HttpStatus.OK);
    }

    @GetMapping("/callProcedureProjection")
    public ResponseEntity<List<IConsultProcDTO>> callProcedureOrFunctionProjection(){
        List<IConsultProcDTO> consults = service.callProcedureOrFunctionProjection();
        return new ResponseEntity<>(consults, HttpStatus.OK);
    }

    @GetMapping(value = "/generateReport", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> generateReport() throws Exception{
        byte[] rspt = service.generateReport();
        return new ResponseEntity<>(rspt, HttpStatus.OK);
    }

    @GetMapping(value = "/generateReport2", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generateReport2() throws Exception{
        byte[] rspt = service.generateReport();
        return new ResponseEntity<>(rspt, HttpStatus.OK);
    }
    //GUARDAR IMAGEN
    @PostMapping(value = "/saveFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveFile(@RequestParam("file")MultipartFile file) throws Exception{
        //BD

        MediaFile mf = new MediaFile();
        mf.setFileType(file.getContentType());
        mf.setFilename(file.getOriginalFilename());
        mf.setValue(file.getBytes());

        mfService.create(mf);

        //Externo
        /*File f = this.convert(file);
         Map response = cloudinary.uploader().upload(f, ObjectUtils.asMap("resource_type", "auto"));
        JSONObject json = new JSONObject(response);
        String url = json.getString("url");
        System.out.println(url);*/
        return new ResponseEntity<>(HttpStatus.OK) ;
    }

    @GetMapping(value = "/readFile/{idFile}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> readFile(@PathVariable("idFile") Integer idFile) throws Exception {
        byte[] arr = mfService.readById(idFile).getValue();

        return new ResponseEntity<>(arr, HttpStatus.OK);
    }

    public File convert(MultipartFile multipartFile) throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("temp", null);

        // Write the content of the MultipartFile to the temporary file
        multipartFile.transferTo(tempFile);

        // At this point, you can use the tempFile as a regular File

        return tempFile;
    }


}
