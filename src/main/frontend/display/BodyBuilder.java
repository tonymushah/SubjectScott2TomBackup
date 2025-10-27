package main.frontend.display;

public class BodyBuilder {
    
    public static String makeNavBar() {
        return """
            <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
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
                                <a class="nav-link" href="http://localhost:8080/SujetScott/page/page1.jsp">
                                    <i class="bi bi-plus-circle me-1"></i>Page d'insertion
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="http://localhost:8080/SujetScott/page/page2.jsp">
                                    <i class="bi bi-search me-1"></i>Page de recherche
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
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
                <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">
                <link rel="stylesheet" href="http://localhost:8080/SujetScott/asset/style.css">
            </head>
            <body>
            """ + BodyBuilder.makeNavBar() + """
            <main class="container-fluid py-4">
                <div class="row justify-content-center">
                    <div class="col-12 col-md-10 col-lg-8 col-xl-6">
            """ + contenu + """
                    </div>
                </div>
            </main>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
            </body>
            </html>
            """;
    }
}