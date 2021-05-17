package de.tekup.endPoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import de.tekup.service.WhiteTestService;
import de.tekup.soap.models.whitetest.StudentRequest;
import de.tekup.soap.models.whitetest.WhiteTestResponse;

@Endpoint
public class WhiteTestEndPoint {

	@Autowired
	WhiteTestService whiteTestService;

	public static final String nameSpace = "http://www.tekup.de/soap/models/whitetest";

	@PayloadRoot(namespace = nameSpace, localPart = "StudentRequest")
	@ResponsePayload
	public WhiteTestResponse getWhiteTest(@RequestPayload StudentRequest studentRequest) throws Exception {
		return whiteTestService.ValidateWhitetTest(studentRequest);
	}

}

