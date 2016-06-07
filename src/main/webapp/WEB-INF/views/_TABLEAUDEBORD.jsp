
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
        <h1>Adversaires statuts actuels</h1>
  
    <c:forEach items="${listeJoueurs}" var="uneCarte">
        <div>
            ${uneCarte.pseudo}
            ${uneCarte.nbreCarte}
        </div>
    </c:forEach>


      <h1>Cartes Disponibles</h1>
      