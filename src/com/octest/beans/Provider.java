package com.octest.beans;
import java.io.File;
import java.sql.*;
public interface Provider {
String DRIVER="org.sqlite.JDBC";
File dbfile=new File(".");
String connection_url="jdbc:sqlite:C:\\Users\\MATHMAMA\\eclipse-workspace-jee\\FQSFO\\database\\users.db";
}
