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
  <li>or run java -jar three-sixty-backend-assessment-0.0.1-SNAPSHOT.war  three-sixty-backend-assessment/target</li>
</ol>

</pre>

<h2>Ddatabase</h2>
<pre>This project uses an in-memory database (H2) so that you don't have to install a database in order to run it. </pre>


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





