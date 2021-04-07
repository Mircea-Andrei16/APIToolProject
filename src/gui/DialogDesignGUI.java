package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import construction.FileTypeFilter;
import construction.Resources;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;

public class DialogDesignGUI extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField inputTextField;
	private JTextField outputTextField;

	/**
	 * Create the dialog.
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
		Resources resources = new Resources();

		contentPanel.setLayout(gridBagLayoutContentPanel);

		// creates the openAPI label of the dialog
		JLabel openAPILabel = new JLabel(resources.getLabel("openApiLabel"));
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
		JButton inputBrowseButton = new JButton("Browse");
		addFunctionalityInputBrowseButton(inputBrowseButton, inputTextField);
		GridBagConstraints gridBConstInputBrowseButton = new GridBagConstraints();
		gridBConstInputBrowseButton.insets = new Insets(0, 0, 5, 5);
		gridBConstInputBrowseButton.gridx = 6;
		gridBConstInputBrowseButton.gridy = 1;
		contentPanel.add(inputBrowseButton, gridBConstInputBrowseButton);

		// creates the services label of the dialog
		JLabel servicesLabel = new JLabel(resources.getLabel("servicesLabel"));
		GridBagConstraints gridBConstServicesLabel = new GridBagConstraints();
		gridBConstServicesLabel.anchor = GridBagConstraints.WEST;
		gridBConstServicesLabel.insets = new Insets(0, 0, 5, 5);
		gridBConstServicesLabel.gridx = 0;
		gridBConstServicesLabel.gridy = 2;
		contentPanel.add(servicesLabel, gridBConstServicesLabel);

		// creates and inserts the radio buttons in the dialog
		JRadioButton currencyRadioButton = new JRadioButton("currency");
		GridBagConstraints gridBConstCurrencyRadioButton = new GridBagConstraints();
		gridBConstCurrencyRadioButton.anchor = GridBagConstraints.EAST;
		gridBConstCurrencyRadioButton.insets = new Insets(0, 0, 5, 5);
		gridBConstCurrencyRadioButton.gridx = 1;
		gridBConstCurrencyRadioButton.gridy = 2;
		contentPanel.add(currencyRadioButton, gridBConstCurrencyRadioButton);

		JRadioButton testRadioButton = new JRadioButton("test");
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
		JLabel portsLabel = new JLabel(resources.getLabel("portsLabel"));
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
		JLabel operationsLabel = new JLabel(resources.getLabel("operationsLabel"));
		GridBagConstraints gridBConstOperationsLabel = new GridBagConstraints();
		gridBConstOperationsLabel.anchor = GridBagConstraints.WEST;
		gridBConstOperationsLabel.insets = new Insets(0, 0, 5, 5);
		gridBConstOperationsLabel.gridx = 0;
		gridBConstOperationsLabel.gridy = 4;
		contentPanel.add(operationsLabel, gridBConstOperationsLabel);

		// creates the operations combo box
		JComboBox<String> operationsComboBox = new JComboBox<>();
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
		JCheckBox onlyRequiredContentCheckBox = new JCheckBox("Only Required Content");
		GridBagConstraints gridBConstORCCheckBox = new GridBagConstraints();
		gridBConstORCCheckBox.insets = new Insets(0, 0, 5, 5);
		gridBConstORCCheckBox.gridx = 0;
		gridBConstORCCheckBox.gridy = 5;
		contentPanel.add(onlyRequiredContentCheckBox, gridBConstORCCheckBox);

		// creates and inserts in the dialog the include examples check box
		JCheckBox includeExamplesCheckBox = new JCheckBox("Include Examples");
		GridBagConstraints gridBConstIECheckBox = new GridBagConstraints();
		gridBConstIECheckBox.anchor = GridBagConstraints.WEST;
		gridBConstIECheckBox.insets = new Insets(0, 0, 5, 5);
		gridBConstIECheckBox.gridx = 0;
		gridBConstIECheckBox.gridy = 6;
		contentPanel.add(includeExamplesCheckBox, gridBConstIECheckBox);

		// creates and inserts the output file label
		JLabel outputFileLabel = new JLabel(resources.getLabel("outputFileLabel"));
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
		JButton outputBrowseButton = new JButton("Browse");
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

		JButton okButton = new JButton("Send");
		okButton.setActionCommand("Send");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		addFunctonalityCloseButton(cancelButton);
		buttonPane.add(cancelButton);

	}

	// functionality for the input browse button
	private void addFunctionalityInputBrowseButton(JButton button, JTextField textField) {

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				// chooses the documents directory from hard disk
				JFileChooser fileChooser = new JFileChooser(new File(""));
				// we are looking for .json files
				FileTypeFilter docFilter = new FileTypeFilter(".json", "JSON Files");
				fileChooser.addChoosableFileFilter(docFilter);
				fileChooser.setDialogTitle("Select Location");
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fileChooser.setAcceptAllFileFilterUsed(false);

				if (fileChooser.showSaveDialog(contentPanel) == JFileChooser.APPROVE_OPTION) {
					// Obtaining the url of the file. looks that is the same with the .getFullPath
					// there is a doubt about the using of this two methods
					try {
						@SuppressWarnings("deprecation")
						URL fileURL = fileChooser.getSelectedFile().toURL();
						String fileOut = fileURL.toString();
						textField.setText(fileOut);

					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

	// functionality for the save browse button
	private void addFunctionalityOutputBrowseButton(JButton button, JTextField textField) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				// opens the doucments directory from the hardisk
				JFileChooser fileChooser = new JFileChooser(new File(""));
				// looks only for files with .son extension
				FileTypeFilter docFilter = new FileTypeFilter(".json", "JSON Files");
				fileChooser.addChoosableFileFilter(docFilter);
				fileChooser.setDialogTitle("Select Location");
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileChooser.setAcceptAllFileFilterUsed(true);
				if (fileChooser.showSaveDialog(contentPanel) == JFileChooser.APPROVE_OPTION) {
					// we only select the name
					String fileID = fileChooser.getSelectedFile().getName();
					textField.setText(fileID);
				}
			}
		});

	}

	// functionality for the cancel button
	private void addFunctonalityCloseButton(JButton button) {
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
