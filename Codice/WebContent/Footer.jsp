<%@page import="java.time.LocalDate"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<footer id="footer">
	<div id='returnBar'>
		<a href='#navigation'>
			<label>Torna su</label>
			<img class='up' src="icone/IconTornaSu.svg">
		</a>
	</div>
	<div id='infoBar'>
		<div class='elInfoBar'>
			<label class='titleInfo'>CHI SIAMO</label>
			<div class='subElInfoBar'>
				<a href=''>
					<label class='subElTitle'>About elowerprice</label>
				</a>
			</div>
		</div>
		<div class='elInfoBar'>
			<label class='titleInfo'>HELP</label>
			<div class='subElInfoBar'>
				<a href=''>
					<label class='subElTitle'>Supporto tecnico</label>
				</a>
			</div>
			<div class='subElInfoBar'>
				<a href=''>
					<label class='subElTitle'>Guida agli acquisti</label>
				</a>
			</div>
			<div class='subElInfoBar'>
				<a href=''>
					<label class='subElTitle'>Metodi di pagamento</label>
				</a>
			</div>
			<div class='subElInfoBar'>
				<a href=''>
					<label class='subElTitle'>Guida Credenziali</label>
				</a>
			</div>
			<div class='subElInfoBar'>
				<a href=''>
					<label class='subElTitle'>Guida registrazione</label>
				</a>
			</div>
		</div>
		<div class='elInfoBar'>
			<label class='titleInfo'>DOCUMENTI LEGALI</label>
			<div class='subElInfoBar'>
				<a href=''>
					<label class='subElTitle'>Politica cookie</label>
				</a>
			</div>
			<div class='subElInfoBar'>
				<a href=''>
					<label class='subElTitle'>Garanzia acquisti</label>
				</a>
			</div>
		</div>
	</div>
	<div class='copyright'>
		<span>Copyright © ElowerPrice <%=LocalDate.now().getYear()%>.</span>
	</div>
</footer>
