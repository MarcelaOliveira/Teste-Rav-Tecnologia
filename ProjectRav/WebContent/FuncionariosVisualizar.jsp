<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name='viewport' content='width=device-width, initial-scale=1'>    
    <link rel='stylesheet' type='text/css' href='css/bootstrap.css'>
    <link rel='stylesheet' type='text/css' href='css/style.css'>
    <link rel='stylesheet' type='text/css' href='https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.3.1/components/icon.css'>
    <title>Teste | Rav Tecnologia</title>
</head>
<body>
<br><br>
	<c:if test='${mensagem.existeErros}'>
	<div class='container'>
		<div class="alert alert-danger alert-dismissible fade show" role="alert">
			<span type="button" class="close" data-dismiss="alert" aria-label="Close" aria-hidden="true">&times;</span>
			<c:forEach var='erro' items='${mensagem.erros}'>
  				<h6 style='color: red;'>${erro}</h6>
  			</c:forEach>
		</div>
	</div>
	</c:if>
    
    <div class='container mt-5'>
        <div class='my-3 px-5 py-5 bg-white rounded shadow-sm'>
           
             <div class='row'>
                <div class='col-lg-6 col-12'>
                        <h1 class='text-left'>Funcionários</h1>
                </div>
                <div class='col-lg-6 col-12 d-flex justify-content-end '>
                    <button type='button' class='btn  btn-lg delete mx-lg-3' data-toggle='modal' data-target='#DeletarContas'  onclick='addValores()'><i class='inverted white trash icon'></i>Deletar</button>
                    <button type='button' class='btn btn-lg add' data-toggle='modal' data-target='#AdicionarConta'> <i class='inverted white plus icon' data-toggle='modal' data-target='#AdicinarConta'></i>Adicionar</button>
                </div>
            </div>
           
            <div class='table-responsive my-5'>
                <table class='table'>
                    <thead>
                        <th scope='col'><input class='form-check-input' onclick='selects()' type='checkbox' value=' id='flexCheckDefault'></th>
                        <th scope='col'>Nome</th>
                        <th scope='col'>Email</th>
                        <th scope='col'>Endereço</th>
                        <th scope='col'>Telefone</th>
                        <th scope='col'>Ações</th>
                    </thead>            
                    <tbody>
                        <tr>
                        	<c:choose>
                 				<c:when test='${not empty requestScope["lista"]}'>
                 					<c:set var='funcio' value='${requestScope["lista"]}'></c:set>
                 				</c:when>
                 			</c:choose>
                        	<c:forEach items='${lista}' var='funcio'>
                            <th scope='row'><input class='form-check-input' type='checkbox' name='email' value='${funcio.email}' id='flexCheckDefault'></th> 
                            <th scope='row'><c:out value='${funcio.nome}'></c:out></th> 
                            <td><c:out value='${funcio.email}'></c:out></td>
                            <td><c:out value='${funcio.endereco}'></c:out></td>
                            <td><c:out value='${funcio.telefone}'></c:out></td>
                            <td><i class='inverted orange edit outline icon' data-toggle='modal' data-target='#EditarConta${funcio.id}'><a href=''></a></i><i class='inverted red trash icon' data-toggle='modal' data-target='#DeletarConta${funcio.id}'><a></a></i></td>
                            <!-- Modal para Editar conta -->
							<div id='EditarConta${funcio.id}' class='modal fade' role='dialog'>
    							<div class='modal-dialog'>
        							<div class='modal-content'>
          								<div class='modal-header'>
            								<h4 class='modal-title'>Editar Funcionário</h4>
            								<button type='button' class='close' data-dismiss='modal'>&times;</button>
          								</div>
          								<div class='modal-body'>
           									<form class='form vertical-alignC' action='HomeControle'  method='post'>
           									<input type='hidden' name='form' value='editar'>
           									<input type='hidden' name='id' value='${funcio.id}'>
              									<div class='form-group'>
                									<input type='text' id='nome' class='form-control' name='nome' placeholder='Nome Funcionário' required>
                									<br>
                									<input type='email' name='email' class='form-control' id='email' placeholder='Email funcionário' required>
                									<br>
                									<input type='text' name='endereco' class='form-control' placeholder='Endereço: Rua número-Bairro ' required>
                									<br>
                									<input type='text' name='telefone' class='form-control' placeholder='Telefone' required>
                									<br>
                									<div class='modal-footer'>
            											<input type='submit' value='Editar'  class='btn' style='background-color: #FF8C00; color: #fff;' tabindex='1'/>
         		 									</div>
                									<br>
              									</div>
            								</form>
          								</div>
        							</div>
      							</div>
      							</div>
      							<!-- Modal para confirmar exclusão de conta -->
								<div id='DeletarConta${funcio.id}' class='modal fade' role='dialog'>
      								<div class='modal-dialog'>
        								<div class='modal-content'>
          									<div class='modal-header'>
           									 	<h4 class='modal-title'>Deletar conta de funcionário</h4>
            									<button type='button' class="close" data-dismiss='modal'>&times;</button>
          									</div>
          									<div class='modal-body'>
            									<p>Você tem certeza que deseja deletar essa conta de funcionário?</p>
          									</div>
          									<div class='modal-footer'>
          										<form class='form vertical-alignC' action='HomeControle'  method='post'>
           											<input type='hidden' name='form' value='deletar'>
           											<input type='hidden' name='id' value='${funcio.id}'>
           											<input type='submit' value='Deletar'  class="btn btn-danger" style='background-color: #D90000; color: #fff;' tabindex='1'/>
           										</form>
            									  <button type='button' class='btn close bt' data-dismiss="modal">Cancelar</button>
          									</div>
        								</div>
      								</div>
    							</div>
                        	</tr>               
                   		</tbody>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
<!-- Modal para adicionar conta -->
<div id='AdicionarConta' class='modal fade' role='dialog'>
    <div class='modal-dialog'>
        <div class='modal-content'>
          <div class='modal-header'>
            <h4 class='modal-title'>Adicionar Funcionário</h4>
            <button type='button' class='close' data-dismiss='modal'>&times;</button>
          </div>
          <div class='modal-body'>
           <form class='form vertical-alignC' action='HomeControle'  method='post'>
           <input type='hidden' name='form' value='cadastro'>
              <div class='form-group'>
                <input type='text' id='nome' class='form-control' name='nome' placeholder='Nome funcionário' required>
                <br>
                <input type='email' name='email' class='form-control' id='email' placeholder='Email funcionário' required>
                <br>
                <input type='text' name='endereco' class='form-control' id='email' placeholder='Endereço: Rua número-Bairro ' required>
                <br>
                <input type='tel' name='telefone' class='form-control' placeholder='Telefone' required>
                <br>
                <div class="modal-footer">
                	<input type='submit' value='Cadastrar'  class='btn' style='background-color:#FF8C00; color: #fff;' tabindex='1'/>
         		 </div>
                <br>
              </div>
            </form>
          </div>
        </div>
    </div>
</div>
 <!-- Modal para confirmar exclusão de conta -->
<div id='DeletarContas' class='modal fade' role='dialog'>
     <div class='modal-dialog'>
        <div class='modal-content'>
          	<div class='modal-header'>
           		<h4 class='modal-title'>Deletar conta de funcionários</h4>
            	<button type='button' class="close" data-dismiss='modal'>&times;</button>
          	</div>
          	<div class='modal-body'>
            	<p>Você tem certeza que deseja deletar essas conta de funcionário?</p>
          	</div>
          	<div class='modal-footer'>
          		<form class='form vertical-alignC' action='HomeControle' id='form'  method='post'>
           		<input type='hidden' name='form' value='deletarContas'>
           		<input type='hidden' id='idd' name='idd' values=''/>
           		<input type='submit' value='Deletar'  class="btn" style='background-color: #D90000; color: #fff;' tabindex='1'/>
           	</form>
            <button type='button' class='btn close bt' data-dismiss="modal">Cancelar</button>
          </div>
        </div>
    </div>
</div> 
</body>
        <script type="text/javascript">  
            function selects(){  
                var ele=document.getElementsByName('email');  
                for(var i=0; i<ele.length; i++){  
                    if(ele[i].type=='checkbox')  
                        ele[i].checked=true;  
                }  
            }
            function addValores(){
 
            	var checkbox=document.getElementsByName('email');
            	var values = [];
            	for(var i = 0; i< checkbox.length; i++){
            		if(checkbox[i].checked == true){
            			values.push(checkbox[i].value);
            		}
            	}
            	$('#idd').val(values);
            }
        </script>
<script src='js/jquery.js' ></script>
<script src='js/popper.min.js' ></script>
<script src='js/bootstrap.min.js' ></script>