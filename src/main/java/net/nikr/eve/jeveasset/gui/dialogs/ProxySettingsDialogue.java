/*
 * Copyright 2009
 *    Niklas Kyster Rasmussen
 *    Flaming Candle*
 *
 *  (*) Eve-Online names @ http://www.eveonline.com/
 *
 * This file is part of jEveAssets.
 *
 * jEveAssets is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * jEveAssets is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jEveAssets; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 */

package net.nikr.eve.jeveasset.gui.dialogs;

// <editor-fold defaultstate="collapsed" desc="imports">
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetSocketAddress;
import java.net.Proxy;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import net.nikr.eve.jeveasset.Program;
import net.nikr.eve.jeveasset.gui.shared.JDialogCentered;

// </editor-fold>
/**
 *
 * @author Andrew Wheat
 */
public class ProxySettingsDialogue extends JDialogCentered implements ActionListener {

	public final static String ACTION_CANCEL = "ACTION_CANCEL";
	public final static String ACTION_SAVE = "ACTION_SAVE";

  JCheckBox enableProxy;
  JComboBox proxyTypeField;
  JTextField proxyAddressField;
  JSpinner proxyPortField;

  JCheckBox enableApiProxy;
  JTextField apiProxyField;

  public ProxySettingsDialogue(Program program, Image image) {
    super(program, "Proxy Settings", image);

    JLabel enableProxyLabel = new JLabel("Enable Proxy");
    JLabel proxyTypeLabel = new JLabel("Proxy Type");
    JLabel proxyAddressLabel = new JLabel("Proxy Address");
    JLabel proxyPortLabel = new JLabel("Proxy Port");

    JLabel enableApiLabel = new JLabel("Enable API Proxy");
    JLabel apiProxyLabel = new JLabel("API Proxy Address");

    JButton saveButton = new JButton("Save");
    saveButton.addActionListener(this);
    saveButton.setActionCommand(ACTION_SAVE);

    JButton cancelButton = new JButton("Cancel");
    cancelButton.addActionListener(this);
    cancelButton.setActionCommand(ACTION_CANCEL);

    enableProxy = new JCheckBox();
    enableProxy.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        proxyTypeField.setEnabled(enableProxy.isSelected());
        proxyAddressField.setEnabled(enableProxy.isSelected());
        proxyPortField.setEnabled(enableProxy.isSelected());
      }
    });

    proxyTypeField = new JComboBox(new DefaultComboBoxModel(Proxy.Type.values()));
    proxyTypeField.setEnabled(false);
    proxyTypeField.setPreferredSize(new Dimension(200, (int)proxyTypeField.getPreferredSize().getHeight()));

    proxyAddressField = new JTextField();
    proxyAddressField.setEnabled(false);

    proxyPortField = new JSpinner(new SpinnerNumberModel(0, 0, 65536, 1));
    proxyPortField.setEnabled(false);

    enableApiProxy = new JCheckBox();
    enableApiProxy.setEnabled(false); // not currently implementated. TODO.
    enableApiProxy.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        apiProxyField.setEnabled(enableApiProxy.isSelected());
      }
    });

    apiProxyField = new JTextField();

    // note: the layout is defined in the super class.
    layout.setHorizontalGroup(
      layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
      .addGroup(
        layout.createSequentialGroup()
          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
              .addComponent(enableProxyLabel)
              .addComponent(proxyTypeLabel)
              .addComponent(proxyAddressLabel)
              .addComponent(proxyPortLabel)
              .addComponent(enableApiLabel)
              .addComponent(apiProxyLabel)
            )
          .addGap(10)
          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
              .addComponent(enableProxy)
              .addComponent(proxyTypeField)
              .addComponent(proxyAddressField)
              .addComponent(proxyPortField)
              .addComponent(enableApiProxy)
              .addComponent(apiProxyField)
            )
          )
        .addGap(10)
        .addGroup(layout.createSequentialGroup()
          .addComponent(saveButton, Program.BUTTONS_WIDTH, Program.BUTTONS_WIDTH, Program.BUTTONS_WIDTH)
          .addComponent(cancelButton, Program.BUTTONS_WIDTH, Program.BUTTONS_WIDTH, Program.BUTTONS_WIDTH)
        )
      );

    layout.setVerticalGroup(
      layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(enableProxyLabel, Program.BUTTONS_HEIGHT, Program.BUTTONS_HEIGHT, Program.BUTTONS_HEIGHT)
            .addComponent(enableProxy, Program.BUTTONS_HEIGHT, Program.BUTTONS_HEIGHT, Program.BUTTONS_HEIGHT)
          )
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(proxyTypeLabel, Program.BUTTONS_HEIGHT, Program.BUTTONS_HEIGHT, Program.BUTTONS_HEIGHT)
            .addComponent(proxyTypeField, Program.BUTTONS_HEIGHT, Program.BUTTONS_HEIGHT, Program.BUTTONS_HEIGHT)
          )
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(proxyAddressLabel, Program.BUTTONS_HEIGHT, Program.BUTTONS_HEIGHT, Program.BUTTONS_HEIGHT)
            .addComponent(proxyAddressField, Program.BUTTONS_HEIGHT, Program.BUTTONS_HEIGHT, Program.BUTTONS_HEIGHT)
          )
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(proxyPortLabel, Program.BUTTONS_HEIGHT, Program.BUTTONS_HEIGHT, Program.BUTTONS_HEIGHT)
            .addComponent(proxyPortField, Program.BUTTONS_HEIGHT, Program.BUTTONS_HEIGHT, Program.BUTTONS_HEIGHT)
          )
        .addGap(10)
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(enableApiLabel, Program.BUTTONS_HEIGHT, Program.BUTTONS_HEIGHT, Program.BUTTONS_HEIGHT)
            .addComponent(enableApiProxy, Program.BUTTONS_HEIGHT, Program.BUTTONS_HEIGHT, Program.BUTTONS_HEIGHT)
          )
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(apiProxyLabel, Program.BUTTONS_HEIGHT, Program.BUTTONS_HEIGHT, Program.BUTTONS_HEIGHT)
            .addComponent(apiProxyField, Program.BUTTONS_HEIGHT, Program.BUTTONS_HEIGHT, Program.BUTTONS_HEIGHT)
          )
        .addGap(10)
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
           .addComponent(saveButton, Program.BUTTONS_HEIGHT, Program.BUTTONS_HEIGHT, Program.BUTTONS_HEIGHT)
           .addComponent(cancelButton, Program.BUTTONS_HEIGHT, Program.BUTTONS_HEIGHT, Program.BUTTONS_HEIGHT)
        )
      );
  }

  @Override
  protected JComponent getDefaultFocus() {
    return enableProxy;
  }

  @Override
  protected void windowShown() { }

  @Override
  protected void windowActivated() { }

  @Override
	public void actionPerformed(ActionEvent e) {
		if (ACTION_CANCEL.equals(e.getActionCommand())){
			this.setVisible(false);
		}
		if (ACTION_SAVE.equals(e.getActionCommand())){
			save();
		}
	}

	private void save() {
    if (enableProxy.isSelected()) {
      program.getSettings().setProxy(
                proxyAddressField.getText()
              , (Integer)proxyPortField.getValue()
              , (Proxy.Type)proxyTypeField.getSelectedItem()
              );
    } else {
      program.getSettings().setProxy(null);
    }

    // TODO set the API proxy.

    setVisible(false);
  }

  private void updateValues() {
    Proxy proxy = program.getSettings().getProxy();
    if (proxy == null) {
      enableProxy.setSelected(false);
    } else {
      enableProxy.setSelected(true);
      proxyTypeField.setSelectedItem(proxy.type());
      if (proxy.address() instanceof InetSocketAddress) {
        InetSocketAddress addr = (InetSocketAddress)proxy.address();
        proxyAddressField.setText(String.valueOf(addr.getHostName()));
        proxyPortField.setValue(addr.getPort());
      }
      proxyTypeField.setEnabled(true);
      proxyAddressField.setEnabled(true);
      proxyPortField.setEnabled(true);
    }

    // TODO set the API Proxy fields
//  JCheckBox enableApiProxy;
//  JTextField apiProxyField;

  }

  @Override
  public void setVisible(boolean b) {
    if (b) {
      updateValues();
    }
    super.setVisible(b);

  }
}
