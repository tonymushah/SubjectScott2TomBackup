package main.frontend.display;

public class BodyBuilder {
    
   public static String makeNavBar() {
    return """
        <nav class="navbar navbar-expand-lg navbar-custom">
            <div class="container-fluid">
                <a class="navbar-brand fw-bold" href="http://localhost:8080/SujetScott/page/page1.jsp">
                    <i class="bi bi-database me-2"></i>Serde-Oracle EMP/SALAIRE
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item">
                            <a class="nav-link active" href="http://localhost:8080/SujetScott/page/page1.jsp">
                                <i class="bi bi-plus-circle me-1"></i>Page d'insertion
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="http://localhost:8080/SujetScott/page/page2.jsp">
                                <i class="bi bi-search me-1"></i>Recherche par employee
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="http://localhost:8080/SujetScott/page/page_dept.jsp">
                                <i class="bi bi-search me-1"></i>Recherche par departement
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="http://localhost:8080/SujetScott/page/inf.jsp">
                                <i class="bi bi-info-circle me-1"></i>Informations
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        """;
}
    
public static String makeDefaultBody(String contenu) {
    return """
        <!DOCTYPE html>
        <html>
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">
            <link rel="stylesheet" href="http://localhost:8080/SujetScott/asset/style.css">
            <title>Serde-Oracle EMP/SALAIRE</title>
        </head>
        <body class="d-flex flex-column min-vh-100">
        """ + makeNavBar() + """
            <main class="container-fluid py-4 flex-grow-1">
                <div class="row justify-content-center">
                    <div class="col-12 col-md-10 col-lg-8 col-xl-6">
        """ + contenu + """
                    </div>
                </div>
            </main>
        """ + makeFooter() + """
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        </body>
        </html>
        """;
}
    public static String makeFooter() {
    return """
        <footer class="footer-custom">
            <div class="container">
                <div class="row footer-content">
                    <div class="col-lg-4 col-md-6 mb-4">
                        <a href="http://localhost:8080/SujetScott/page/page1.jsp" class="footer-brand">
                            <i class="bi bi-database me-2"></i>Serde-Oracle EMP/SALAIRE
                        </a>
                        <p class="footer-description mt-3">
                            Application de gestion des employés et salaires. 
                            Interface complète pour l'insertion, la recherche et 
                            la gestion des données Oracle.
                        </p>
                    </div>
                    
                    <div class="col-lg-2 col-md-6 mb-4">
                        <div class="footer-links">
                            <h5>Navigation</h5>
                            <ul>
                                <li>
                                    <a href="http://localhost:8080/SujetScott/page/page1.jsp">
                                        <i class="bi bi-plus-circle"></i>Insertion
                                    </a>
                                </li>
                                <li>
                                    <a href="http://localhost:8080/SujetScott/page/page2.jsp">
                                        <i class="bi bi-search"></i>Recherche
                                    </a>
                                </li>
                                <li>
                                    <a href="http://localhost:8080/SujetScott/page/inf.jsp">
                                        <i class="bi bi-info-circle"></i>Infos
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    
                    <div class="col-lg-3 col-md-6 mb-4">
                        <div class="footer-contact">
                            <h5>Contact</h5>
                            <div class="contact-info">
                                <p>
                                    <i class="bi bi-envelope"></i>
                                    support@serdeoracle.com
                                </p>
                                <p>
                                    <i class="bi bi-globe"></i>
                                    localhost:8080
                                </p>
                                <p>
                                    <i class="bi bi-database"></i>
                                    Oracle Database
                                </p>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-lg-3 col-md-6 mb-4">
                        <div class="footer-contact">
                            <h5>Technologies</h5>
                            <div class="contact-info">
                                <p>
                                    <i class="bi bi-code-slash"></i>
                                    JSP/Servlets
                                </p>
                                <p>
                                    <i class="bi bi-bootstrap"></i>
                                    Bootstrap 5
                                </p>
                                <p>
                                    <i class="bi bi-server"></i>
                                    Oracle DB
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="footer-bottom">
                    <div class="row align-items-center">
                        <div class="col-md-6">
                            <p>&copy; 2024 Serde-Oracle EMP/SALAIRE. Tous droits réservés.</p>
                        </div>
                        <div class="col-md-6">
                            <div class="social-links">
                                <a href="#" title="GitHub">
                                    <i class="bi bi-github"></i>
                                </a>
                                <a href="#" title="Documentation">
                                    <i class="bi bi-book"></i>
                                </a>
                                <a href="#" title="Support">
                                    <i class="bi bi-life-preserver"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
        
        <style>
            /* Le CSS personnalisé du footer doit être inclus ici */
        </style>
        """;
}
}