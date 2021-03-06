package com.dbhakuni.learning.jax.rs.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.dbhakuni.learning.bean.CalcBean;


@Path("/calc")
@Component
public class CalculatorClass {

	public CalculatorClass() {
		System.out.println("CalculatorClass :: New Object ");
	}
	/*
	 * http://localhost:8080/WebServiceProj/rest/calc/add;a=5;b=5
	 * good
	 */
	@GET @Path("/add")
	@Produces("text/plain")
	public Response add(@MatrixParam("a")int a, @MatrixParam("b")int b){
		return Response.ok().entity(String.valueOf(a+b)).build();
	}
	
	/*
	 * http://localhost:8080/WebServiceProj/rest/calc/sub;a=15;b=5
	 */
	@GET @Path("/sub")
	@Produces("text/plain")
	public Response sub(@MatrixParam("a")int a, @MatrixParam("b")int b){
		return Response.ok().entity(String.valueOf(a-b)).build();
	}
	/*
	 * http://localhost:8080/WebServiceProj/rest/calc/
	 * {"num1":"2","num2":"2","operation":"+"}
	 */
	@Path("/") @POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response calculator(CalcBean bean){
		
		if(bean == null){
			bean = new CalcBean();
		}else{

			if("+".equals(bean.getOperation())){
				bean.setResult(bean.getNum1()+bean.getNum2());
			}else if("-".equals(bean.getOperation())){
				bean.setResult(bean.getNum1()-bean.getNum2());
			}else if("*".equals(bean.getOperation())){
				bean.setResult(bean.getNum1()*bean.getNum2());
			}else if("/".equals(bean.getOperation())){
				bean.setResult(bean.getNum1()/bean.getNum2());
			}
		}
		System.out.println(bean);
		return Response.ok(bean).build();
	}
}
