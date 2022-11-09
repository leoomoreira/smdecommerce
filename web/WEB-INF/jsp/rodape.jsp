<%-- 
    Document   : rodape
    Author     : Leonardo Oliveira Moreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
            </main>
            <footer>
                <div class="p-3 bg-white border-top text-dark text-center">&copy; 2022</div>
            </footer>
        </div>    
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" type="text/javascript"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
        <script src="js/index.js" type="text/javascript"></script>
    </body>
</html>
<%
    } else {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Logout");
        dispatcher.forward(request, response);
    }
%>