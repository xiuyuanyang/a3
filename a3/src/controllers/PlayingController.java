package controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import beans.Mi;
import beans.Qiu;
import business.Game;

@Controller
@RequestMapping("/paopao")
public class PlayingController {

	private Game game= new Game();
	
	@RequestMapping(value = "/updateQiu", method = RequestMethod.POST)
	@ResponseBody
	public String setQiu(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		BufferedReader br  = req.getReader();
		String str = IOUtils.toString(br);
		JSONObject jo = JSON.parseObject(str);
		
		String id = jo.getString("id");
		Qiu q = jo.getObject("qiu", Qiu.class);
		
		q = game.setQiu(id, q);
		
		String result = JSON.toJSONString(q);
		System.out.println("updateQiu return = "+result);
		return result;
	}
	
	@RequestMapping(value = "/loadQius", method = RequestMethod.GET)
	@ResponseBody
	public String loadQius(HttpServletRequest req, HttpServletResponse res) throws IOException {
			
		String result = JSON.toJSONString(game.getQiu_group().values());
		
		return result;
	}
	
	@RequestMapping(value = "/loadMis", method = RequestMethod.GET)
	@ResponseBody
	public String loadMis(HttpServletRequest req, HttpServletResponse res) throws IOException {

		String result = JSON.toJSONString(game.getMi_group());
		return result;
	}
	
	@RequestMapping(value = "/produceMis", method = RequestMethod.GET)
	@ResponseBody
	public String produceMis(HttpServletRequest req, HttpServletResponse res) throws IOException {
		double x , y , r ; String yanse;
		Mi mm;
		for(int i=0;i<5;i++){
			x = Math.random()*1000;
			y = Math.random()*1000;
			r = 2;
			yanse = "black";
			mm = new Mi(x , y , r , yanse);
			game.addMi(mm);
		}
		String result = JSON.toJSONString(game.getMi_group());
		return result;
	}
	
	
	@RequestMapping(value = "/zhuceQiu", method = RequestMethod.POST)
	@ResponseBody
	public String addQiu(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		BufferedReader br  = req.getReader();
		String str = IOUtils.toString(br);
		JSONObject jo = JSON.parseObject(str);
		
		String id = jo.getString("id");
		
		Qiu q = jo.getObject("qiu", Qiu.class);
		
		int r = game.addQiu(q, id);
		if(r>0){
			String result = JSON.toJSONString(q);
			return result;
		}
		return "0";	
	}
	
	@RequestMapping(value = "/tt", method = RequestMethod.POST)
	@ResponseBody
	public String tt(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		BufferedReader br  = req.getReader();
		String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine())!=null){
            sb.append(line);
        }
		
		System.out.println("str = "+ sb);
		
		Qiu q = JSON.parseObject(sb.toString(), Qiu.class);
		
		
		System.out.println("q.getYanse = "+q.getYanse());
				
		String result = JSON.toJSONString(q);
		
		return result;
	}
	
}
