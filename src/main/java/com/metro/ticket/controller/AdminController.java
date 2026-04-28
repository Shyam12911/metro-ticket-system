package com.metro.ticket.controller;

import com.metro.ticket.service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /* ========= LOGIN ========= */
    @PostMapping("/login")
    public String login(@RequestParam String username,
            @RequestParam String password,
            HttpSession session) {

        if ("admin".equals(username) && "admin123".equals(password)) {
            session.setAttribute("ADMIN", true);
            return "OK";
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
    }

    /* ========= LOGOUT ========= */
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "OK";
    }

    /* ========= CHECK ========= */
    private void check(HttpSession session) {
        if (!Boolean.TRUE.equals(session.getAttribute("ADMIN"))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
    }

    /* ========= STATS ========= */
    @GetMapping("/stats")
    public Object stats(HttpSession session) {
        check(session);
        return adminService.getStats();
    }

    /* ========= ALL TICKETS ========= */
    @GetMapping("/tickets")
    public Object allTickets(HttpSession session) {
        check(session);
        return adminService.getAllTickets();
    }

    /* ========= INVALIDATE ========= */
    @PostMapping("/tickets/{id}/invalidate")
    public Object invalidate(@PathVariable Long id, HttpSession session) {
        check(session);

        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ticket id required");
        }

        return adminService.invalidateTicket(id);
    }

    /* ========= EXPORT ========= */
    @GetMapping("/tickets/export")
    public String export(HttpSession session) {
        check(session);
        return adminService.exportCsv();
    }
}