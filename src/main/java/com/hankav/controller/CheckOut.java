package com.hankav.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hankav.model.Item;

/**
 * Servlet implementation class CheckOut
 */
@WebServlet("/CheckOut")
public class CheckOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=(String) request.getSession().getAttribute("username");
		List<Object[]>items=(List<Object[]>) request.getSession().getAttribute("cartitems");
		if(username==null) {
			request.getRequestDispatcher("register").forward(request,response);
			return;
		}
		else {
			String currency=(String) request.getSession().getAttribute("currency");
			List<Item>itemstopaypal=new ArrayList<>();
			for(Object[] obj:items) {
				Item item=new Item();
				item.setName(obj[0].toString());
				Integer months=Integer.parseInt(obj[2].toString());
				item.setMonths(months);
				Double price=Double.parseDouble(obj[1].toString());
				if(currency.equals("USD")) {
					item.setPrice(price*months);
				}
				else if(currency.equals("EUR")) {
					item.setPrice(price*months*0.87);
				}
				else if(currency.equals("GBP")) {
					item.setPrice(price*months*0.79);
				}
				itemstopaypal.add(item);
			}
		}
	}

}
