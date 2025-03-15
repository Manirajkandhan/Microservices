/*
 * package com.abc.order_service.controller;
 * 
 * import org.springframework.beans.factory.annotation.Value; import
 * org.springframework.cloud.context.config.annotation.RefreshScope; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * @RefreshScope
 * 
 * @RestController public class DemoController {
 * 
 * @Value("${myname}") private String name;
 * 
 * @Value("${message}") private String msg;
 * 
 * @GetMapping("/data") public String getData() {
 * 
 * return "My Name is:"+name; }
 * 
 * @GetMapping("/config/data") public String getDataFromConfigServer() {
 * 
 * return "Data from config server:"+msg; } }
 */