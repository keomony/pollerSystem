package com.kry.controllers;

import com.kry.exceptions.PollerException;
import com.kry.models.Poller;
import com.kry.services.PollerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class PollerController {

    private final PollerService pollerService;

    @GetMapping("/addnew")
    public String showAddNewForm(Poller poller) {
        return "add-poller";
    }

    @PostMapping("/addpoller")
    public String addPoller(@Validated Poller poller, BindingResult result) throws PollerException {
        if (result.hasErrors()) {
            return "add-poller";
        }

        pollerService.save(poller);
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String showPollerList(Model model) {
        model.addAttribute("pollers", pollerService.retrieveAllPollers());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Poller poller = pollerService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid service Id:" + id));

        model.addAttribute("poller", poller);
        return "update-poller";
    }

    @PostMapping("/update/{id}")
    public String updatePoller(@PathVariable("id") Integer id, @Validated Poller poller, BindingResult result) throws PollerException {

        if (result.hasErrors()) {
            poller.setId(id);
            return "update-poller";
        }

        pollerService.save(poller);

        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deletePoller(@PathVariable("id") Integer id) throws PollerException {
        Poller poller = pollerService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid service Id:" + id));
        pollerService.delete(poller.getId());

        return "redirect:/index";
    }
}
