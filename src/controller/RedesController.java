package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {

	public RedesController() {
		super();
	}
	/*Método chamado "os", identifica e retorna o sistema operacional
	para os métodos "ip" e "ping". (método privado)*/
	private String os() {
		//operação que identifica o nome do SO com retorno
		String opeSystem = System.getProperty("os.name");
		return opeSystem;
	}
	//Metodo chamado "ip", recebe o nome do SO do metodo privado "os"
	public void ip(String caminhoCmd) {
		
		//instanciamento da classe chamando o método privado "os" pegando o nome do SO
		RedesController opeSystem = new RedesController();
		String so = opeSystem.os();
		
//		C:\\Windows\\System32\\cmd.exe (caminho do prompt de comando)		
//		cmd /c start C:\\Windows\\System32\\cmd.exe (prompt do Windows como ADM)
//		sudo su \\usr\\bin\\exo-open (Terminal do Linux como root)		
//		Verificação do nome do SO
		if(so.contains("Windows")) {
			caminhoCmd = "cmd /c start C:\\Windows\\System32\\cmd.exe";
		}else {
			caminhoCmd = "sudo su \\usr\\bin\\exo-open";
		}
		
		try {
			//Execucao do comando para abrir o prompt de comando
			Runtime.getRuntime().exec(caminhoCmd.toString());
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuffer buffer = new StringBuffer();
		if(so.contains("Windows")) {
			buffer.append("ipconfig");
		}else {
			buffer.append("ifconfig");
		}
		
		    try {
		    	Process	processoCmd = Runtime.getRuntime().exec(buffer.toString());
		    	InputStream fluxo = processoCmd.getErrorStream();
		    	InputStreamReader leitor = new InputStreamReader(fluxo);
		    	BufferedReader buffer2 = new BufferedReader(leitor);
		    	String linha = buffer2.readLine();
		    	String bufferNovo = " ";
		    	
		    	System.out.println("Exibindo apenas os adaptadores que possuem IPV4:\n");
		    	
		    	while(linha != null) {
		    		linha = buffer2.readLine();
		    		
		    	if(linha !=null && linha.contains ("Adaptador") || linha.contains("mtu")) {
		    		bufferNovo = linha;
		    	}
		    	    if(linha != null && linha.contains("Adaptador") || linha.contains("inet")) {
		    	    	bufferNovo = linha;
		    	    }
		    	}
		    	
		    	buffer2.close();
		    	leitor.close();
		    	fluxo.close();
		    }catch (IOException e) {
		    	e.printStackTrace();
		    }
		    
		    System.exit(0);
		    
	}
	
	public void ping(String caminhoCmd) {
		//instanciamento da classe chamando o metodo privado "os"pegando o nome do SO
		RedesController opeSystem = new RedesController();
		String so = opeSystem.os();
		
//		C:\\Windows\\System32\\cmd.exe (caminho do prompt de comando)		
//		cmd /c start C:\\Windows\\System32\\cmd.exe (prompt do Windows como ADM)
//		sudo su \\usr\\bin\\exo-open (Terminal do Linux como root)		
//		Verificação do nome do SO
		if(so.contains("Windows")) {
			caminhoCmd = "cmd /c start C:\\Windows\\System32\\cmd.exe";
		}else {
			caminhoCmd = "sudo su \\usr\\bin\\exo-open";
		}
		
		    try {
		    	//Execucao do comando para abrir o prompt de comando
		    	Runtime.getRuntime().exec(caminhoCmd.toString());
		        }catch (IOException e) {
		    	e.printStackTrace();
		    }
		    
		        StringBuffer buffer = new StringBuffer();
		        if(so.contains("Windows")) {
		    	buffer.append("ping -4 -n 10 www.google.com.br");
		        }else {
		    	buffer.append("ping -4 -c 10 www.google.com.br");
		         }
		        
		    try {
		    	Process processoCmd = Runtime.getRuntime().exec(buffer.toString());
		    	InputStream fluxo = processoCmd.getErrorStream();
		    	InputStreamReader leitor = new InputStreamReader(fluxo);
		    	BufferedReader buffer2 = new BufferedReader(leitor);
		    	String linha = buffer2.readLine();
		    	while(linha != null) {
		    		linha = buffer2.readLine();
		    		
		    		if(linha != null && linha.contains("ms,") || linha.contains("/mdev =")) {
		    			System.out.println(linha);
		    		}
		    	}
		    	buffer2.close();
		    	leitor.close();
		    	fluxo.close();
		    	
		    }catch (IOException e) {
		    	e.printStackTrace();
		    }
		    System.exit(0);
	}
}
		    
		    		