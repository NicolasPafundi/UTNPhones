package com.utn.TPFinal.controllers;

import com.utn.TPFinal.exceptions.ResourceNotExistException;
import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.model.dtos.CallInput;
import com.utn.TPFinal.model.dtos.CallsReportFilter;
import com.utn.TPFinal.model.entities.*;
import com.utn.TPFinal.model.projections.InfraResponse;
import com.utn.TPFinal.model.projections.ReportCallsByUserByDate;
import com.utn.TPFinal.services.CallService;
import com.utn.TPFinal.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.utn.TPFinal.Utils.RestUtils.GetLocation;

@RestController("")
@RequestMapping("/api/Call")
public class CallController {

    private final CallService callService;
    private final SessionManager sessionManager;

    @Autowired
    public CallController(CallService callService, SessionManager sessionManager) {
        this.callService = callService;
        this.sessionManager = sessionManager;
    }


    @GetMapping("/Employee/")
    public ResponseEntity<List<Call>> getAll(@RequestHeader("Authorization") String sessionToken){
        try{
            List<Call> calls = callService.getAll();
            return (calls.size() > 0) ? ResponseEntity.ok(calls) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/Employee/{id}")
    public ResponseEntity<Call> getById(@RequestHeader("Authorization") String sessionToken,@PathVariable Integer id) throws ResourceNotExistException, Exception {
        try{
            Call call= callService.getById(id);
            return (call != null) ? ResponseEntity.ok(call) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/Employee/User/{id}")
    public ResponseEntity<List<Call>> getByUserId(@RequestHeader("Authorization") String sessionToken,@PathVariable Integer id) throws ResourceNotExistException, Exception {
        try{
            List<Call> calls = callService.getByUserId(id);
            return (calls.size() > 0) ? ResponseEntity.ok(calls) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/Employee/reportCallsByUserByDate")
    public  ResponseEntity<List<ReportCallsByUserByDate>> getReportCallsByUserByDate(@RequestHeader("Authorization") String sessionToken, @RequestBody CallsReportFilter callsReportFilter) throws ResourceNotExistException, Exception {
        try{
            sessionManager.getCurrentUser(sessionToken).getId();
            List<ReportCallsByUserByDate> calls = callService.getReportCallsByUserByDate(callsReportFilter);
            return (calls.size() > 0) ? ResponseEntity.ok(calls) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        } catch (Exception ex) {
            throw ex;
        }
    }

    @GetMapping("/Client/me")
    public ResponseEntity<List<Call>> getByCurrentUser(@RequestHeader("Authorization") String sessionToken) throws ResourceNotExistException, Exception {
        try{
            Integer userId = sessionManager.getCurrentUser(sessionToken).getId();
            List<Call> calls = callService.getByUserId(userId);
            return (calls.size() > 0) ? ResponseEntity.ok(calls) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/Infrastructure/")
    public ResponseEntity add(@RequestHeader("Authorization") String sessionToken, @RequestBody CallInput call){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(callService.createCall(call));
        }catch (Exception ex){
            throw ex;
        }
    }

    @PutMapping("/Infrastructure/")
    public ResponseEntity update(@RequestHeader("Authorization") String sessionToken,@RequestBody Call call) throws Exception, ResourceNotExistException {
        try{
            callService.update(call);
            return ResponseEntity.ok().build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @DeleteMapping("/Infrastructure/{id}")
    public ResponseEntity remove(@RequestHeader("Authorization") String sessionToken,@PathVariable Integer id) throws ResourceNotExistException, Exception {
        try{
            callService.remove(id);
            return ResponseEntity.ok().build();
        }catch (Exception ex){
            throw ex;
        }
    }

}
