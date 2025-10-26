package main.frontend.display;

public class BodyBuilder {
    
    public static String makeNavBar() {
        return """
            <nav class="navbar">
                <div class="nav-container">
                    <div class="nav-logo">
                        <a href="http://localhost:8080/SujetScott/page/page1.jsp">Serde-Oracle EMP/SALAIRE</a>
                    </div>
                    <ul class="nav-menu">
                        <li class="nav-item">
                            <a href="http://localhost:8080/SujetScott/page/page1.jsp" class="nav-link">Page 1</a>
                        </li>
                        <li class="nav-item">
                            <a href="http://localhost:8080/SujetScott/page/page2.jsp" class="nav-link">Page 2</a>
                        </li>
                        <li class="nav-item">
                            <a href="http://localhost:8080/SujetScott/page/inf.jsp" class="nav-link">Informations</a>
                        </li>
                    </ul>
                    <div class="hamburger">
                        <span class="bar"></span>
                        <span class="bar"></span>
                        <span class="bar"></span>
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
                <link rel="stylesheet" href="http://localhost:8080/SujetScott/asset/style.css">
            </head>
            <body>
            """ + BodyBuilder.makeNavBar() + """
            <main class="main-content">
                <div class="form-container">
            """ + contenu + """
                </div>
            </main>
            </body>
            </html>
            """;
    }
}