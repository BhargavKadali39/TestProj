package org.team4.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SprintController {

	@RequestMapping(value = "/sprints", method = RequestMethod.GET)
	public String getAllSprints(Model model) {
		System.out.println("Sprint List JSP Requested");

		// Retrieve all sprints from the database and add them to the model

		return "sprint_home";
	}

	@RequestMapping(value = "/sprint_details", method = RequestMethod.GET)
	public String getSprintDetails(Model model) {
		System.out.println("Sprint Details JSP Requested");

		// Retrieve the selected sprint details from the database and add them to the model

		return "sprint_details";
	}

	@RequestMapping(value = "/projectDetails", method = RequestMethod.GET)
	public String getProjectDetails(Model model) {
		System.out.println("Sprint Details JSP Requested");

		// Retrieve the selected sprint details from the database and add them to the model

		return "projectDetails";
	}

	@RequestMapping(value = "/add_sprint", method = RequestMethod.GET)
	public String addSprint(Model model) {
		System.out.println("Add Sprint JSP Requested");

		// Add any necessary data to the model for rendering the add sprint page

		return "add_sprint";
	}
}
