package com.application.medCareApplication;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import com.application.medCareApplication.view.MainFrame;

@SpringBootApplication
public class MedCareApplication {

	public static void main(String[] args) {
		//SpringApplication.run(MedCareApplication.class, args);
		new SpringApplicationBuilder(MedCareApplication.class).headless(false).web(WebApplicationType.NONE).run(args);
	}

	/*@Bean
    public MainFrame getMainFrame() {
		try {
			UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel");
			UIManager.put("Synthetica.tabbedPane.keepOpacity", true);
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
        return MainFrame.getInstance();
    }*/
}
