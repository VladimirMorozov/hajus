<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html>
	<head>
	</head>
	<body>
		body be here <s:property value="message"></s:property>
		<s:iterator value="events">
			<s:property value="title"></s:property>
		</s:iterator>
		<p>----</p>
		<s:iterator value="persons">
			<s:property value="firstname"></s:property>
			<s:iterator value="events">
				my event: <s:property value="title"></s:property>
			</s:iterator>
		</s:iterator>
	</body>
</html>