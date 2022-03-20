<h1>SM360 Backend Tech Assignment</h1>

This is a sample Java / Maven / Spring Boot (version 2.6.4) Listing application.

<h2>Requirements</h2>
<pre><ol>
  <li>Java - 11</li>
   <li>JMaven - 4.x.x</li>
</ol></pre>

<h2>How to run</h2>
<pre><ol>
  <li>git clone https://github.com/Boukataya/three-sixty-backend-assessment.git</li>
  <li>open src/main/resources/application.properties and change <code>spring.datasource.username</code> and <code>spring.datasource.password</code>
  <li>run mysql</li>
  <li>use Intellij or eclipse to build the project, | Alternatively, you can run the app without packaging it using mvn spring-boot:run</li>
  <li>The app will start running at http://localhost:8080.</li>
</ol>

</pre>

<h2>Documentation and endpoints</h2>

<pre><ol>
  <li>More details in http://localhost:8080/v2/api-docs</li>
  <li>Test the endpoints http://localhost:8080/swagger-ui/index.html</li>
</ol></pre>

<h2>The app defines following functionalities.</h2>

<pre>

POST /api/register-dealer

POST /api/{dealerId}/save-listing

PUT /api/publish/{listingId}

GET /api/search/{dealerId}/{state}

PUT /api/unpublish/{listingId}

PUT /api/update-listing/{listingId}
</pre>
You can test them using postman or any other rest client.





