
Ext.define('Eway.view.field.card.TerminalId', {
	extend: 'Ext.form.field.Text',
	alias: 'widget.card_TerminalId',

	fieldLabel : EwayLocale.commen.terminalId,
	name : 'terminalId',
	maxLength:20,
	vtype : "terminalId"

});