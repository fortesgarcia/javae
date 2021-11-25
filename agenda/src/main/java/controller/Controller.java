package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.JavaBeans;

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/editar", "/alterarContato", "/delete", "/report" })
public class Controller extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The dao. */
	DAO dao = new DAO();
	
	/** The contato. */
	JavaBeans contato = new JavaBeans();

	/**
	 * Instantiates a new controller.
	 */
	public Controller() {
		super();
	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		if (action.equals("/main")) {
			contatos(request, response);
		} else if (action.equals("/insert")) {
			addContato(request, response);
		} else if (action.equals("/editar")) {
			listarContato(request, response);
		} else if (action.equals("/alterarContato")) {
			alterarContato(request, response);
		} else if (action.equals("/delete")) {
			deletarContato(request, response);
		} else if (action.equals("/report")) {
			gerarRelatorio(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

	/**
	 * Contatos.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Listar contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// criando objeto que ira receber os dados javabeans
		ArrayList<JavaBeans> lista = dao.listaContato();
		// popula dados na view
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}

	/**
	 * Adds the contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// inserir contatos
	protected void addContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// setar variaveis JavaBeans
		contato.setNomeContato(request.getParameter("nome"));
		contato.setFoneContato(request.getParameter("fone"));
		contato.setEmailContato(request.getParameter("email"));

		dao.inserirContato(contato);
		// redirecionar agenda.jsp
		response.sendRedirect("main");
	}

	/**
	 * Listar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// listar contatos
	protected void listarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Setar a variável JavaBeans
		contato.setIdContato(request.getParameter("idcon"));
		// Executar o método selecionar contato (DAO)
		dao.selecionarContato(contato);
		// Setar as informações na View
		request.setAttribute("id", contato.getIdContato());
		request.setAttribute("nome", contato.getNomeContato());
		request.setAttribute("fone", contato.getFoneContato());
		request.setAttribute("email", contato.getEmailContato());
		// encaminhar para a View
		RequestDispatcher rd = request.getRequestDispatcher("editarContato.jsp");
		rd.forward(request, response);

		contato.setIdContato(request.getParameter("id"));
		contato.setNomeContato(request.getParameter("nome"));
		contato.setFoneContato(request.getParameter("fone"));
		contato.setEmailContato(request.getParameter("email"));

	}

	/**
	 * Alterar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// alterar contatos
	protected void alterarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Setar as informações na View
		contato.setIdContato(request.getParameter("id"));
		contato.setNomeContato(request.getParameter("nome"));
		contato.setFoneContato(request.getParameter("fone"));
		contato.setEmailContato(request.getParameter("email"));
		// Executar o método update contato (DAO)
		dao.updateContato(contato);
		// redirecionar agenda.jsp atualizando as alterações
		response.sendRedirect("main");
	}

	/**
	 * Deletar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// deletar contatos
	protected void deletarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Executar o método update contato (DAO)
		contato.setIdContato(request.getParameter("id"));
		// Executa o método deleta contato (DAO)
		dao.deletaContato(contato);
		// redirecionar agenda.jsp atualizando as alterações
		response.sendRedirect("main");
	}

	/**
	 * Gerar relatorio.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// gerar relatorio em pdf
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Document documento = new Document();

		try {
			// tipo de conteudo
			response.setContentType("application/pdf");
			// nome do documento
			response.addHeader("Content-Disposition", "inline;filename=" + "contatos.pdf");
			// criar o documento
			PdfWriter.getInstance(documento, response.getOutputStream());
			// abrir o documento para gerar o conteudo
			documento.open();
			documento.add(new Paragraph("Lista de Contatos:"));
			documento.add(new Paragraph(" "));
			// criar uma tabela
			PdfPTable tabela = new PdfPTable(3);
			// cabeçalho
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Email"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			// popular a tabela com os contatos
			ArrayList<JavaBeans> lista = dao.listaContato();
			for (int i = 0; i < lista.size(); i++) {
				tabela.addCell(lista.get(i).getNomeContato());
				tabela.addCell(lista.get(i).getFoneContato());
				tabela.addCell(lista.get(i).getEmailContato());
			}
			documento.add(tabela);
			documento.close();
		} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
	}
}
