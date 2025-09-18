package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    private final InvoiceDAO dao;

    public InvoiceController(InvoiceDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public String listInvoices(Model model) {
        model.addAttribute("invoices", dao.findAll());
        model.addAttribute("newInvoice", new Invoice());
        return "invoice";
    }

    @PostMapping("/add")
    public String addInvoice(@ModelAttribute("newInvoice") Invoice invoice) {
        // compute total
        double sub = invoice.getQty() * invoice.getRate();
        double discAmt = sub * invoice.getDiscount() / 100.0;
        double taxable = sub - discAmt;
        double gstAmt = taxable * invoice.getGst() / 100.0;
        double total = taxable + gstAmt;

        invoice.setTotal(total);
        dao.save(invoice);

        return "redirect:/invoice";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        dao.deleteById(id);
        return "redirect:/invoice";
    }
}
