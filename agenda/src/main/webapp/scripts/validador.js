/**
 * Valida se os dados foram preenchidos corretamente
 * Faltar setar uma mascara e validar o campo telefone, atualmente permite qualquer numero
 */
function validar(){
	let nome =frmContato.nome.value
	let fone =frmContato.fone.value
	
	if(nome === "" ){
		alert('Verifique o campo Nome')
		frmContato.nome.focus()
		return false
	}else if(fone === "" ){
		alert('Verifique o campo Telefone') 
		frmContato.fone.focus()
		return false
	}else {
		document.forms["frmContato"].submit()
	}
}
