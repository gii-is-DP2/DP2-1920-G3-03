<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ attribute name="name" required="true" rtexprvalue="true"
	description="Name of the active menu: home, owners, vets or error"%>


<nav class="navbar navbar-default" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand"
				href="<spring:url value="/" htmlEscape="true" />"><span></span>
			</a>
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#main-navbar">
				<span class="sr-only"><os-p>Toggle navigation</os-p></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>
		<div class="navbar-collapse collapse" id="main-navbar">
			<ul class="nav navbar-nav">
				
				<!-- Todos -->
										
				<petclinic:menuItem active="${name eq 'home'}" url="/"
					title="home page">
					<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
					<span>Home</span>
				</petclinic:menuItem>
				
				<petclinic:menuItem active="${name eq 'owners'}" url="/ejercicios/find"
					title="Ejercicios">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					<span>Ejercicios</span>
				</petclinic:menuItem>
								
				<petclinic:menuItem active="${name eq 'vets'}" url="/vets"
					title="Maquinas">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					<span>Maquinas</span>
				</petclinic:menuItem>
				
				<petclinic:menuItem active="${name eq 'vets'}" url="/vets"
					title="Retos">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					<span>Retos</span>
				</petclinic:menuItem>
	
				<petclinic:menuItem active="${name eq 'error'}" url="/oups"
					title="trigger a RuntimeException to see how it is handled">
					<span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>
					<span>Error</span>
				</petclinic:menuItem>
								
				<!-- Admin -->
				
				<sec:authorize access="hasAuthority('admin')">		
				
					<petclinic:menuItem active="${name eq 'vets'}" url="/vets"
						title="Retos">
						<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
						<span>Retos</span>
					</petclinic:menuItem>
					
					<petclinic:menuItem active="${name eq 'vets'}" url="/vets"
						title="Clases">
						<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
						<span>Clases</span>
					</petclinic:menuItem>
					
					<petclinic:menuItem active="${name eq 'vets'}" url="/vets"
						title="Dashboard">
						<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
						<span>Dashboard</span>
					</petclinic:menuItem>	
					
					<petclinic:menuItem active="${name eq 'vets'}" url="/vets"
						title="Empleados">
						<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
						<span>Empleados</span>
					</petclinic:menuItem>
					
				</sec:authorize>
				
				<!-- Usuario -->
				
				<sec:authorize access="hasAuthority('user')">
				
				<ul class="nav navbar-nav">
				
					<petclinic:menuItem active="${name eq 'vets'}" url="/vets"
						title="Entrenamientos">
						<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
						<span>Mis Entrenamientos</span>
					</petclinic:menuItem>
					
					<petclinic:menuItem active="${name eq 'vets'}" url="/vets"
						title="Rutinas">
						<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
						<span>Rutinas</span>
					</petclinic:menuItem>
					
					<petclinic:menuItem active="${name eq 'vets'}" url="/vets"
						title="Dietas">
						<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
						<span>Dietas</span>
					</petclinic:menuItem>
					
					<petclinic:menuItem active="${name eq 'vets'}" url="/vets"
						title="Clases">
						<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
						<span>Clases</span>
					</petclinic:menuItem>
					
					<petclinic:menuItem active="${name eq 'vets'}" url="/vets"
						title="Dashboard">
						<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
						<span>Dashboard</span>
					</petclinic:menuItem>		
				</ul>
				
				</sec:authorize>
					
				
				<!-- Entrenador -->
				<sec:authorize access="hasAuthority('trainer')">
				
					<petclinic:menuItem active="${name eq 'vets'}" url="/vets"
						title="Rutinas">
						<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
						<span>Mis Alumnos</span>
					</petclinic:menuItem>
					
					<petclinic:menuItem active="${name eq 'vets'}" url="/vets"
						title="Rutinas">
						<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
						<span>Gestion Rutinas</span>
					</petclinic:menuItem>
					
					<petclinic:menuItem active="${name eq 'vets'}" url="/vets"
						title="Dietas">
						<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
						<span>Gestion Dietas</span>
					</petclinic:menuItem>
					
					<petclinic:menuItem active="${name eq 'vets'}" url="/vets"
						title="Dashboard">
						<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
						<span>Dashboard</span>
					</petclinic:menuItem>		

			</sec:authorize>
			</ul>
			

			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="!isAuthenticated()">
					<li><a href="<c:url value="/login" />">Login</a></li>
					<li><a href="<c:url value="/users/new" />">Register</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <span class="glyphicon glyphicon-user"></span> 
							<strong><sec:authentication property="name" /></strong> <span
							class="glyphicon glyphicon-chevron-down"></span>
					</a>
						<ul class="dropdown-menu">
							<li>
								<div class="navbar-login">
									<div class="row">
										<div class="col-lg-4">
											<p class="text-center">
												<span class="glyphicon glyphicon-user icon-size"></span>
											</p>
										</div>
										<div class="col-lg-8">
											<p class="text-left">
												<strong><sec:authentication property="name" /></strong>
											</p>
											<p class="text-left">
												<a href="<c:url value="/logout" />"
													class="btn btn-primary btn-block btn-sm">Logout</a>
											</p>
										</div>
									</div>
								</div>
							</li>
							<li class="divider"></li>
						
                            <li> 
								<div class="navbar-login navbar-login-session">
									<div class="row">
										<div class="col-lg-12">
											<p>
												<a href="#" class="btn btn-primary btn-block">My Profile</a>
												<a href="#" class="btn btn-danger btn-block">Change
													Password</a>
											</p>
										</div>
									</div>
								</div>
							</li>

						</ul></li>
				</sec:authorize>
			</ul>
		</div>



	</div>
</nav>
