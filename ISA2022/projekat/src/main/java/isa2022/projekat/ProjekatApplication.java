package isa2022.projekat;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

import isa2022.projekat.dtos.UpitnikDTO;
import isa2022.projekat.model.data.Termin;
import isa2022.projekat.model.data.Upitnik;
import isa2022.projekat.repositories.TerminRepository;
import isa2022.projekat.repositories.UpitnikRepository;
import isa2022.projekat.services.RegKorisnikService;

//Copyright 2020 Esri
//Licensed under the Apache License, Version 2.0 (the "License");
//you may not use this file except in compliance with the License.
//You may obtain a copy of the License at
//
//http://www.apache.org/licenses/LICENSE-2.0
//
//Unless required by applicable law or agreed to in writing, software
//distributed under the License is distributed on an "AS IS" BASIS,
//WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//See the License for the specific language governing permissions and
//limitations under the License.

//import com.esri.arcgisruntime.concurrent.ListenableFuture;
//import com.esri.arcgisruntime.geometry.Geometry;
//import com.esri.arcgisruntime.mapping.BasemapStyle;
//import com.esri.arcgisruntime.mapping.Viewpoint;
//import com.esri.arcgisruntime.mapping.view.Graphic;
//import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
//import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
//import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
//import com.esri.arcgisruntime.tasks.networkanalysis.Route;
//import com.esri.arcgisruntime.tasks.networkanalysis.RouteParameters;
//import com.esri.arcgisruntime.tasks.networkanalysis.RouteResult;
//import com.esri.arcgisruntime.tasks.networkanalysis.RouteTask;
//import com.esri.arcgisruntime.tasks.networkanalysis.Stop;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ListChangeListener;
//import javafx.collections.ObservableList;
//import javafx.geometry.Point2D;
//import javafx.geometry.Pos;
//import javafx.scene.control.Alert;
//import javafx.scene.control.ListView;
//import javafx.scene.input.MouseButton;
//import java.util.List;
//
//import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
//import com.esri.arcgisruntime.mapping.ArcGISMap;
//import com.esri.arcgisruntime.mapping.view.MapView;
//
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.layout.StackPane;
//import javafx.stage.Stage;


@SpringBootApplication
@EnableEurekaClient
public class ProjekatApplication {

	
//	private MapView mapView;
//	private Graphic routeGraphic;
//	private static RouteParameters routeParameters;
//	private static RouteTask routeTask;
	public static void main(String[] args) throws InterruptedException{
		SpringApplication.run(ProjekatApplication.class, args);
		
//		String url = "https://router.project-osrm.org/route/v1/driving/19.564765,44.9975019;20.46,44.81?overview=false&alternatives=true&steps=true&hints=;";
//		ResponseEntity<String> temp = new RestTemplate().getForEntity(url, String.class);
//		String test = temp.getBody();
//		GsonBuilder gson = new GsonBuilder();
//		Object o = gson.create().fromJson(test, Object.class);
		
//		JsonNode obj = new ObjectMapper().readTree(test);
//		JsonNode niz = obj.get("routes").get(0);
		
//		HttpClient
		
//		parse(test);
//		System.out.println("STRING ODGOVOR: " + temp);
		
	}

//	public static void parse(String json) throws JsonMappingException, JsonProcessingException  {
//	       JsonFactory factory = new JsonFactory();
//
//	       ObjectMapper mapper = new ObjectMapper(factory);
//	       JsonNode rootNode = mapper.readTree(json);  
//	       Iterator<Map.Entry<String,JsonNode>> fieldsIterator = rootNode.fields();
//	       while (fieldsIterator.hasNext()) {
//
//	           Map.Entry<String,JsonNode> field = fieldsIterator.next();
//	           System.out.println("Key: " + field.getKey() + "\tValue:" + field.getValue());
//	       }
//	}
}
