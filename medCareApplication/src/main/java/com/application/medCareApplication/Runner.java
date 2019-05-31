

package com.application.medCareApplication;

import java.sql.SQLException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.application.medCareApplication.model.Patient;
import com.application.medCareApplication.utils.handler.DatabaseHandler;
import com.application.medCareApplication.view.MainFrame;

@Component
public class Runner implements CommandLineRunner {

	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		 
		/**
	     * Pull in the JFrame to be displayed.
	     */
		java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
            	try {
        			UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel");
        			UIManager.put("Synthetica.tabbedPane.keepOpacity", true);
        		} catch (ClassNotFoundException | InstantiationException
        				| IllegalAccessException | UnsupportedLookAndFeelException e) {
        			e.printStackTrace();
        		}
            	
                MainFrame.getInstance().setVisible(true);
               /* DatabaseHandler dh = new DatabaseHandler();
                dh.select();
                try {
					dh.create(new Patient());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                dh.select();*/
               
            }
        });

	   
	}

}
