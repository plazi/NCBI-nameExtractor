package org.plazi.name_extractor.webservice;

import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.plazi.name_extractor.domain.Status;

/**
 * 
 * @author philipp.kuntschik@htwchur.ch
 *
 */
@Path("/")
public class WebserviceContract {

	private Logger l = Logger.getLogger(getClass().getName());




	@GET
	@Path("/status")
	@Produces("application/json")
	public Status status() {
		l.info("WEBSERVICE: status called...");
		return new Status();
	}

}
