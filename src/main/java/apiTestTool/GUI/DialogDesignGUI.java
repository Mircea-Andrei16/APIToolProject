/**
 * GUI for this project. Creates a Dialog for the project
 */
package apiTestTool.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import org.json.simple.parser.ParseException;

import apiTestTool.construction.FileTypeFilter;
import apiTestTool.construction.Resources;
import apiTestTool.business.OpenAPISchema;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;

public class DialogDesignGUI extends JDialog {

// **************************************************
// Fields
// **************************************************

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField inputTextField;
	private JTextField outputTextField;
	private String outputFileAbsolutePath;
	private OpenAPISchema apiSchema;
	private JComboBox<String> operationsComboBox;

// **************************************************
// Public method
// **************************************************

	/**
	 * /** Create the dialog with the specific buttons
	 * 
	 * @param displays on the screen at the specific rectangle of the screen the
	 *                 Dialog
	 */
	public DialogDesignGUI() {

		// set the frame of the of the dialog
		setBounds(100, 100, 550, 400);
		setTitle("Open APIChecker");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gridBagLayoutContentPanel = new GridBagLayout();

		// creates the rows and columns for the GridBagLayout
		gridBagLayoutContentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayoutContentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayoutContentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gridBagLayoutContentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };

		contentPanel.setLayout(gridBagLayoutContentPanel);

		// creates the openAPI label of the dialog
		JLabel openAPILabel = new JLabel(Resources.INPUT_URL_LABEL);
		GridBagConstraints gridConstOpenAPILabel = new GridBagConstraints();
		gridConstOpenAPILabel.insets = new Insets(0, 0, 5, 5);
		gridConstOpenAPILabel.anchor = GridBagConstraints.WEST;
		gridConstOpenAPILabel.gridx = 0;
		gridConstOpenAPILabel.gridy = 1;
		contentPanel.add(openAPILabel, gridConstOpenAPILabel);

		// creates the first text field of the dialog
		inputTextField = new JTextField();
		GridBagConstraints gridBConstTextField = new GridBagConstraints();
		gridBConstTextField.gridwidth = 5;
		gridBConstTextField.insets = new Insets(0, 0, 5, 5);
		gridBConstTextField.fill = GridBagConstraints.HORIZONTAL;
		gridBConstTextField.gridx = 1;
		gridBConstTextField.gridy = 1;
		contentPanel.add(inputTextField, gridBConstTextField);
		inputTextField.setColumns(20);

		// creates the input browse button
		JButton inputBrowseButton = new JButton(Resources.BROWSE_BUTTONS);
		addFunctionalityInputBrowseButton(inputBrowseButton, inputTextField);
		GridBagConstraints gridBConstInputBrowseButton = new GridBagConstraints();
		gridBConstInputBrowseButton.insets = new Insets(0, 0, 5, 5);
		gridBConstInputBrowseButton.gridx = 6;
		gridBConstInputBrowseButton.gridy = 1;
		contentPanel.add(inputBrowseButton, gridBConstInputBrowseButton);

		// creates the services label of the dialog
		JLabel servicesLabel = new JLabel(Resources.SERVICES_LABEL);
		GridBagConstraints gridBConstServicesLabel = new GridBagConstraints();
		gridBConstServicesLabel.anchor = GridBagConstraints.WEST;
		gridBConstServicesLabel.insets = new Insets(0, 0, 5, 5);
		gridBConstServicesLabel.gridx = 0;
		gridBConstServicesLabel.gridy = 2;
		contentPanel.add(servicesLabel, gridBConstServicesLabel);

		// creates and inserts the radio buttons in the dialog
		JRadioButton currencyRadioButton = new JRadioButton(Resources.CURRENCY_BUTTON_LABEL);
		GridBagConstraints gridBConstCurrencyRadioButton = new GridBagConstraints();
		gridBConstCurrencyRadioButton.anchor = GridBagConstraints.EAST;
		gridBConstCurrencyRadioButton.insets = new Insets(0, 0, 5, 5);
		gridBConstCurrencyRadioButton.gridx = 1;
		gridBConstCurrencyRadioButton.gridy = 2;
		contentPanel.add(currencyRadioButton, gridBConstCurrencyRadioButton);

		JRadioButton testRadioButton = new JRadioButton(Resources.TEST_BUTTON_LABEL);
		GridBagConstraints gridBConstTestRadioButton = new GridBagConstraints();
		gridBConstTestRadioButton.insets = new Insets(0, 0, 5, 5);
		gridBConstTestRadioButton.gridx = 2;
		gridBConstTestRadioButton.gridy = 2;
		contentPanel.add(testRadioButton, gridBConstTestRadioButton);

		// group the radio buttons for choosing one button at a time
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(currencyRadioButton);
		buttonGroup.add(testRadioButton);

		// creates and inserts the port label
		JLabel portsLabel = new JLabel(Resources.PORTS_LABEL);
		GridBagConstraints gridBConstPortsLabel = new GridBagConstraints();
		gridBConstPortsLabel.anchor = GridBagConstraints.WEST;
		gridBConstPortsLabel.insets = new Insets(0, 0, 5, 5);
		gridBConstPortsLabel.gridx = 0;
		gridBConstPortsLabel.gridy = 3;
		contentPanel.add(portsLabel, gridBConstPortsLabel);

		// creates the ports combo box
		JComboBox<String> portsComboBox = new JComboBox<>();
		portsComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "soap1", "soap2" }));
		GridBagConstraints gridBConstPortsComboBox = new GridBagConstraints();
		gridBConstPortsComboBox.fill = GridBagConstraints.HORIZONTAL;
		gridBConstPortsComboBox.insets = new Insets(0, 0, 5, 5);
		gridBConstPortsComboBox.gridx = 1;
		gridBConstPortsComboBox.gridy = 3;
		contentPanel.add(portsComboBox, gridBConstPortsComboBox);

		// creates and inserts the operations label
		JLabel operationsLabel = new JLabel(Resources.OPERATIONS_LABEL);
		GridBagConstraints gridBConstOperationsLabel = new GridBagConstraints();
		gridBConstOperationsLabel.anchor = GridBagConstraints.WEST;
		gridBConstOperationsLabel.insets = new Insets(0, 0, 5, 5);
		gridBConstOperationsLabel.gridx = 0;
		gridBConstOperationsLabel.gridy = 4;
		contentPanel.add(operationsLabel, gridBConstOperationsLabel);

		// creates the operations combo box
	    operationsComboBox = new JComboBox<>();
		operationsComboBox
				.setModel(new DefaultComboBoxModel<>(new String[] { "getRealTime", "getTime", "getTimeAsString" }));
		GridBagConstraints gridBConstOperationsComboBox = new GridBagConstraints();
		gridBConstOperationsComboBox.gridwidth = 2;
		gridBConstOperationsComboBox.insets = new Insets(0, 0, 5, 5);
		gridBConstOperationsComboBox.fill = GridBagConstraints.HORIZONTAL;
		gridBConstOperationsComboBox.gridx = 1;
		gridBConstOperationsComboBox.gridy = 4;
		contentPanel.add(operationsComboBox, gridBConstOperationsComboBox);
		
		
		// creates and inserts in the dialog the only required content check box
		JCheckBox onlyRequiredContentCheckBox = new JCheckBox(Resources.CHECKBOX_ONLY_REQUIRED_CONTENT_NAME);
		GridBagConstraints gridBConstORCCheckBox = new GridBagConstraints();
		gridBConstORCCheckBox.insets = new Insets(0, 0, 5, 5);
		gridBConstORCCheckBox.gridx = 0;
		gridBConstORCCheckBox.gridy = 5;
		contentPanel.add(onlyRequiredContentCheckBox, gridBConstORCCheckBox);

		// creates and inserts in the dialog the include examples check box
		JCheckBox includeExamplesCheckBox = new JCheckBox(Resources.CHECKBOX_INCLUDE_EXAMPLES);
		GridBagConstraints gridBConstIECheckBox = new GridBagConstraints();
		gridBConstIECheckBox.anchor = GridBagConstraints.WEST;
		gridBConstIECheckBox.insets = new Insets(0, 0, 5, 5);
		gridBConstIECheckBox.gridx = 0;
		gridBConstIECheckBox.gridy = 6;
		contentPanel.add(includeExamplesCheckBox, gridBConstIECheckBox);

		// creates and inserts the output file label
		JLabel outputFileLabel = new JLabel(Resources.OUTPUT_FILE_LABEL);
		GridBagConstraints gridBConstOutputFileLabel = new GridBagConstraints();
		gridBConstOutputFileLabel.anchor = GridBagConstraints.WEST;
		gridBConstOutputFileLabel.insets = new Insets(0, 0, 0, 5);
		gridBConstOutputFileLabel.gridx = 0;
		gridBConstOutputFileLabel.gridy = 7;
		contentPanel.add(outputFileLabel, gridBConstOutputFileLabel);

		// the second text field
		outputTextField = new JTextField();
		GridBagConstraints gridBConstOutputFileTextField = new GridBagConstraints();
		gridBConstOutputFileTextField.gridwidth = 5;
		gridBConstOutputFileTextField.insets = new Insets(0, 0, 0, 5);
		gridBConstOutputFileTextField.fill = GridBagConstraints.HORIZONTAL;
		gridBConstOutputFileTextField.gridx = 1;
		gridBConstOutputFileTextField.gridy = 7;
		contentPanel.add(outputTextField, gridBConstOutputFileTextField);
		outputTextField.setColumns(20);

		// output browse button
		JButton outputBrowseButton = new JButton(Resources.BROWSE_BUTTONS);
		// add the functionality on the browse button
		addFunctionalityOutputBrowseButton(outputBrowseButton, outputTextField);
		GridBagConstraints gridBConstOutputBrowseButton = new GridBagConstraints();
		gridBConstOutputBrowseButton.insets = new Insets(0, 0, 0, 5);
		gridBConstOutputBrowseButton.gridx = 6;
		gridBConstOutputBrowseButton.gridy = 7;
		contentPanel.add(outputBrowseButton, gridBConstOutputBrowseButton);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton sendButton = new JButton("Send");
		addFunctionalitySendButton(sendButton);
		sendButton.setActionCommand("Send");
		buttonPane.add(sendButton);
		getRootPane().setDefaultButton(sendButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		addFunctionalityCloseButton(cancelButton);
		buttonPane.add(cancelButton);

	}

// **************************************************
// Private methods
// **************************************************

	/**
	 * Creates the action listener for the input browse button Using the open dialog
	 * from JFileChooser, opens a file from the disk or an URL from browser to be
	 * edited Places in the inputTextFIeld the URL of the .json file
	 * 
	 * @param inputBrowseButton
	 */
	private void addFunctionalityInputBrowseButton(final JButton button, final JTextField textField) {

		button.addActionListener(new ActionListener() {
			/**
			 * Add functionality for the inputBrowseButton, for opening a file from disk and
			 * place the URL of the file in the text field
			 */
			@Override
			public void actionPerformed(ActionEvent evt) {
				// chooses the documents directory from hard disk
				JFileChooser fileChooser = new JFileChooser(new File(""));
				// we are looking only for .json files
				FileTypeFilter docFilter = new FileTypeFilter(Resources.FILE_EXTENTION, "JSON Files");
				fileChooser.addChoosableFileFilter(docFilter);
				fileChooser.setDialogTitle("Select Location");
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fileChooser.setAcceptAllFileFilterUsed(false);
				// opens a JFileChooser dialog for opening a file
				int retval = fileChooser.showOpenDialog(button);
				if (retval == JFileChooser.APPROVE_OPTION) {
					// Obtaining the url of the file using try and catch method.
					try {
						@SuppressWarnings("deprecation")
						URL fileURL = fileChooser.getSelectedFile().toURL();
						String fileOut = fileURL.toString();
						textField.setText(fileOut);
						
						//call the OpenAPISchema that will extract the paths from the .json file
						apiSchema  = new OpenAPISchema(fileOut.substring(6));
						
						// getter for returning the number of paths that exists in the .json file 
						int apiSize = apiSchema.returnPathsSize();
					
						// add the paths to the operationComboBox
						for (int iterator = 0;iterator < apiSize;iterator ++) {
							
							operationsComboBox.addItem(apiSchema.getPath(iterator).substring(1));
						}
						
						//remove the example elements from the operationsC0mboBox
						operationsComboBox.removeItemAt(2);
						operationsComboBox.removeItemAt(1);
						operationsComboBox.removeItemAt(0);
						
					} catch (MalformedURLException e) {
						e.printStackTrace();
			} catch (IOException e) {
						e.printStackTrace();
					} catch (ParseException e) {
				e.printStackTrace();
			}
		  }
			}
		});
	}

	/**
	 * Creates the action listener for the output/save browse button Using the save
	 * dialog JFileChooser chooses a file from the disk or create a new one in the
	 * filed name Places in the outputTextField the name with the extention on the
	 * file
	 * 
	 * @param outputBrowseButton
	 */
	private void addFunctionalityOutputBrowseButton(final JButton button, final JTextField textFieldOutput) {
		button.addActionListener(new ActionListener() {
			@Override
			/**
			 * Add functionality for the outputBrowseButton, for choosing a file from the
			 * disk to store the new information of the API or creating a new one by write
			 * the name of the new file
			 */
			public void actionPerformed(ActionEvent evt) {
				JFileChooser fileChooser = new JFileChooser() {
					/**
					 * Here we create a panel that attention us if we want to overwrite a file from
					 * the disk
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void approveSelection() {
						File file = getSelectedFile();
						if (file.exists() && getDialogType() == SAVE_DIALOG) {
							int result = JOptionPane.showConfirmDialog(this, "The file exists, overwrite?",
									"Existing file", JOptionPane.YES_NO_CANCEL_OPTION);
							switch (result) {
							case JOptionPane.YES_OPTION:
								super.approveSelection();
								return;
							case JOptionPane.NO_OPTION:
								return;
							case JOptionPane.CLOSED_OPTION:
								return;
							case JOptionPane.CANCEL_OPTION:
								cancelSelection();
								return;
							default:
								return;
							}
						}
						super.approveSelection();
					}
				};

				// the doc filter for the file that we want to choose
				FileTypeFilter docFilter = new FileTypeFilter(Resources.FILE_EXTENTION, "JSON Files");
				fileChooser.addChoosableFileFilter(docFilter);
				fileChooser.setDialogTitle("Select Location");
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileChooser.setAcceptAllFileFilterUsed(true);

				// the save dialog from JFileChooser
				int retval = fileChooser.showSaveDialog(button);
				if (retval == JFileChooser.APPROVE_OPTION) {
					// we use a string for storing the absolute path. that helps for the
					// verification if the file is valid.
					outputFileAbsolutePath = fileChooser.getSelectedFile().getAbsolutePath();
					String fileID = fileChooser.getSelectedFile().getName();
					int firstOccurrence = fileID.indexOf(Resources.FILE_EXTENTION);
					// if it is created a new file we have to display the file name plus extention
					if (firstOccurrence == -1) {
						fileID += Resources.FILE_EXTENTION;
					}
					textFieldOutput.setText(fileID);
				}
			}
		});

	}

	/**
	 * This method implements the functionality of the sendButton. It shows a panel
	 * that tell us if the URL and the Output file are valid It has additional
	 * panels if it is forgotten to place an input file or a output file
	 * 
	 * @param button
	 */
	private void addFunctionalitySendButton(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {

				// display the panel with the error message that the input file in not placed
				if (inputTextField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(contentPanel, "The input text box is empty.", "Major error",
							JOptionPane.ERROR_MESSAGE);
				} else {

					// display the panel with the error message that the output file in not selected
					if (outputTextField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(contentPanel, "The output text box is empty.", "Major error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						// use two methods that checks in the URL and The Output file are valid
						String urlField = inputTextField.getText();
						boolean flagInternetURL = verifyURLValid(urlField);
						boolean flagOutoutFile = verifyFileExists();
						// display the message
						JOptionPane.showMessageDialog(contentPanel,
								"The URL is " + flagInternetURL + " and the Output File is " + flagOutoutFile);
					}
				}
			}
		});

	}

	/**
	 * Verify if the URL is valid
	 * 
	 * @param verifyURL
	 * @return true if is Valid or false if is not Valid
	 */
	private boolean verifyURLValid(String verifyURL) {

		try {
			new URL(verifyURL).toURI();
			return true;
		} catch (Exception e) {
			Path path = Paths.get(verifyURL.substring(6));
			boolean flagFileURL = Files.exists(path);

			if (flagFileURL)
				return flagFileURL;

			return false;

		}
	}

	/**
	 * Verify if the selected file is Valid
	 * 
	 * @param verifyFile
	 * @return true if it is or false if isn't
	 */
	private boolean verifyFileExists() {
		Path path = Paths.get(outputFileAbsolutePath);
		boolean flagFile = Files.exists(path);
		if (flagFile)
			return flagFile;
		return false;
	}

	/**
	 * If is pressed it closes the Dialog
	 * 
	 * @param closeButton
	 */
	private void addFunctionalityCloseButton(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				// close the dialog
				System.exit(0);
			}
		});
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogDesignGUI dialog = new DialogDesignGUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
