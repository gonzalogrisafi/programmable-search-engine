package ar.edu.ubp.das.resources;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ar.edu.ubp.das.beans.PreferencesBean;
import ar.edu.ubp.das.db.Dao;
import ar.edu.ubp.das.db.DaoFactory;
import ar.edu.ubp.das.logging.MyLogger;
import ar.edu.ubp.das.security.Secured;

@Path("settings")
public class PreferencesResource {
	
	private MyLogger logger;
	
	public PreferencesResource() {
		this.logger = new MyLogger(this.getClass().getSimpleName());
	}

	@Context
	ContainerRequestContext req;

	@GET
	@Path("ping")
	public Response ping() {
		this.logger.log(MyLogger.INFO, "Petición de ping exitosa");
		return Response.ok().entity("pong").build();
	}

	@GET
	@Secured
	public Response getPreferences() {
		try {
			Dao<PreferencesBean, PreferencesBean> dao = DaoFactory.getDao("Preferences", "ar.edu.ubp.das");
			this.logger.log(MyLogger.INFO, "Petición de preferencias exitosa");
			return Response.ok().entity(dao.find((Integer) req.getProperty("id"))).build();
		} catch (SQLException e) {
			this.logger.log(MyLogger.ERROR, "Petición de preferencias con error:: " + e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(PreferencesBean preferences) {
		try {
			Dao<PreferencesBean, Integer> dao = DaoFactory.getDao("Preferences", "ar.edu.ubp.das");
			preferences.setUserId((Integer) req.getProperty("id"));
			dao.update(preferences);
			this.logger.log(MyLogger.INFO, "Actualización de preferencias exitosa");
			return Response.status(Status.NO_CONTENT).build();
		} catch (Exception e) {
			this.logger.log(MyLogger.ERROR, "Actualización de preferencias con error: " + e.getMessage());
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
}
