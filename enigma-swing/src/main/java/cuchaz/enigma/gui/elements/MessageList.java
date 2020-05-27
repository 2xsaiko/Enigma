package cuchaz.enigma.gui.elements;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

import cuchaz.enigma.gui.util.AbstractListCellRenderer;
import cuchaz.enigma.gui.util.GridBagConstraintsBuilder;
import cuchaz.enigma.gui.util.ScaleUtil;
import cuchaz.enigma.utils.validation.Message;
import cuchaz.enigma.utils.validation.ParameterizedMessage;

public class MessageList {

	private final JPanel ui = new JPanel(new GridBagLayout());

	private final JCheckBox confirmAllCheckBox = new JCheckBox("Confirm All");
	private final JList<MessageItem> messageList = new JList<>(new MessageItem[]{
			new MessageItem(new ParameterizedMessage(Message.ILLEGAL_IDENTIFIER, new Object[]{"fuk you", " ", 4})),
			new MessageItem(new ParameterizedMessage(Message.ILLEGAL_IDENTIFIER, new Object[]{"fuk you", " ", 4})),
			new MessageItem(new ParameterizedMessage(Message.ILLEGAL_IDENTIFIER, new Object[]{"fuk you", " ", 4})),
			new MessageItem(new ParameterizedMessage(Message.ILLEGAL_IDENTIFIER, new Object[]{"fuk you", " ", 4})),
	});
	private final JScrollPane messageListScrollPane = new JScrollPane(this.messageList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	public MessageList() {
		this.messageList.setCellRenderer(new MessageItemRenderer());
		this.messageListScrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
		this.confirmAllCheckBox.setHorizontalTextPosition(JCheckBox.LEFT);

		GridBagConstraintsBuilder b = GridBagConstraintsBuilder.create()
				.insets(4);

		this.ui.add(this.confirmAllCheckBox, b.pos(1, 0).anchor(GridBagConstraints.EAST).build());
		this.ui.add(this.messageListScrollPane, b.dimensions(0, 1, 2, 1).weight(1.0, 1.0).fill(GridBagConstraints.BOTH).build());
	}

	public JPanel getUi() {
		return this.ui;
	}

	private static final class MessageItem {
		public final ParameterizedMessage message;

		private MessageItem(ParameterizedMessage message) {
			this.message = message;
		}
	}

	private static final class MessageItemRenderer extends AbstractListCellRenderer<MessageItem> {

		private final JCheckBox confirmCheckBox = new JCheckBox();
		private final JToggleButton expandButton = new JToggleButton();
		private final JLabel shortDescLabel = new JLabel();
		private final JLabel longDescLabel = new JLabel();

		public MessageItemRenderer() {
			this.setLayout(new GridBagLayout());
			this.expandButton.setPreferredSize(ScaleUtil.getDimension(20, 20));
			this.confirmCheckBox.setOpaque(false);

			GridBagConstraintsBuilder b = GridBagConstraintsBuilder.create()
					.insets(4);

			this.add(this.confirmCheckBox, b.pos(1, 0).build());
			this.add(this.expandButton, b.pos(2, 0).build());
			this.add(this.shortDescLabel, b.pos(0, 0).weight(1.0, 0.0).anchor(GridBagConstraints.WEST).build());
			this.add(this.longDescLabel, b.dimensions(0, 1, 3, 1).weight(1.0, 1.0).anchor(GridBagConstraints.WEST).build());
		}

		@Override
		public void updateUiForEntry(JList<? extends MessageItem> list, MessageItem value, int index, boolean isSelected, boolean cellHasFocus) {
//			this.confirmCheckBox.setVisible(value.message.message.type == Type.WARNING);
			this.confirmCheckBox.setVisible(true);
			this.shortDescLabel.setText(value.message.getText());
			String longText = value.message.getLongText();
			if (longText.trim().isEmpty()) {
				this.longDescLabel.setVisible(false);
				this.expandButton.setVisible(false);
			} else {
				this.longDescLabel.setVisible(true);
				this.expandButton.setVisible(true);
				this.longDescLabel.setText(longText);
			}
		}

	}

}
