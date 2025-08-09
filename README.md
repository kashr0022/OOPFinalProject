# OOPFinalProject
repo for cst8288 final project. please make a clone and create a branch to work off of.
<table>
<tr>
  <th>Name</th>
  <th>Student Number</th>
  <th>Email</th>
</tr>
<tr>
  <td>Khairunnisa Ashri</td>
  <td>041164870</td>
  <td>ashr0022@algonquinlive.com</td>
</tr>
<tr>
  <td>Lily S.</td>
  <td>041003275</td>
  <td>schm0137@algonquinlive.com</td>
</tr>
<tr>
  <td>Francesca Parent</td>
  <td>041063843</td>
  <td>pare0242@algonquinlive.com</td>
</tr>
<tr>
  <td>Oussema Faleh</td>
  <td>041152127</td>
  <td>fale0014@algonquinlive.com</td>
</tr>
<table>
  <h2>Version Log</h2>
<tr>
  <th>Version #</th>
  <th>Last Updated By</th>
  <th>Notes</th>
  <th>Date</th>
</tr>
<tr>
  <td>1</td>
  <td>Francesca Parent, Khairunnisa Ashri, Lily S.</td>
  <td>Introduction done by Francesca</td>
  <td>July 3, 2025</td>
  
</tr>
<tr>
  <td>1.1</td>
  <td>Lily S.</td>
  <td>Added target audience & scope sections</td>
  <td>July 13th, 2025</td>
</tr>
<tr>
  <td>1.2</td>
  <td>Khairunnisa Ashri</td>
  <td>Sub headings and some examples added</td>
  <td>July 14th, 2025</td>
</tr>
<tr>
  <td>1.3</td>
  <td> ...  </td>
  <td> ... </td>
  <td> ... </td>
</tr>
<tr>
  <td>2</td>
  <td>Khairunnisa Ashri</td>
  <td>Added version log table to GitHub, finished DB script, new ERD, new commit.</td>
  <td>July 29th, 2025</td>
</tr>
<tr>
  <td>2.1</td>
  <td>Lily S.</td>
  <td>Added required dependencies to pom.xml file</td>
  <td>July 30th, 2025</td>
</tr>
<tr>
  <td>2.2</td>
  <td>Lily S.</td>
  <td>Presentation slide structure finished, assigning out slides to individual members.</td>
  <td>Aug 1st, 2025</td>
</tr>
<tr>
  <td>2.3</td>
  <td>Lily S.</td>
  <td>Base dataSource functionality implemented</td>
  <td>August 1st, 2025</td>
</tr>
<tr>
  <td>2.4</td>
  <td>Lily S.</td>
  <td> 
      <ul>
        <li>Log-in authentication functionality added Register functionality added</li>
        <li>UsersDTO + StaffDTO added</li>
        <li>Corresponding DAO methods added</li>
        <li>Log-on servlet in FC added</li>
        <li>Registration servlet added</li>
        <li>Light global CSS styling added (including palette variables)</li>
      </ul>
</td>
  <td>August 3rd, 2025</td>
</tr>
<tr>
  <td>2.5</td>
  <td>Lily S.</td>
  <td>Log-out functionality added
Checks in database for duplicate username/first+last combo prevention during register added
</td>
  <td>August 3rd, 2025</td>
</tr>
<tr>
  <td>2.6</td>
  <td>Lily S.</td>
  <td>
     <ul>
        <li>FR02 - Vehicle Management basic functionality + servlet added.</li>
        <li> Builder + simple factory implemented for this FR </li>
        <li>Corresponding DAO methods added</li>
        <li>DB script adjustments</li>
      </ul>
   </td>
  <td>August 4th, 2025</td>
</tr>
<tr>
  <td>2.7</td>
  <td>Khairunnisa Ashri</td>
  <td>
     <ul>
        <li>FR06 - Reporting & Analytics partially fulfilled.</li>
        <li>necessary classes have been added</li>
        <li>.sql changes</li>
        <li>more changes to .sql or existing files to be made on next update to get all data displayed</li>
      </ul>
   </td>
  <td>August 4th, 2025</td>
</tr>
<tr>
  <td>2.8</td>
  <td>Khairunnisa Ashri</td>
  <td>
     <ul>
        <li>FR06 - Reporting & Analytics (dashboards) functional</li>
        <li>FR04 - Monitoring Energy/Fuel Consumption and FR05 Predictive Maintenance partially fulfilled</li>
        <li>necessary class and .sql changes made</li>
        <li>missing expenses/cost reports</li>
      </ul>
   </td>
  <td>August 4th, 2025</td>
</tr>
<tr>
  <td>2.9</td>
  <td>Khairunnisa Ashri</td>
  <td>
     <ul>
        <li>FR06 - Reporting & Analytics complete</li>
        <li>added cost report and navigation from dashboard back to controller</li>
        <li>edited controller button for dashboard</li>
   </td>
  <td>August 4th, 2025</td>
</tr>
  <tr>
    <td><strong>3.0</strong></td>
    <td><strong>Lily S.</strong></td>
    <td>
      <ul>
        <li>Cleaned up data access folder; removed unused Dao files, moved everything into PTFMSDao alongside implementing required methods into parent interface.</li>
        <li>Deleted other DAOs once merged, renamed all references to those DAOs and implemented calls to them in the business logic.</li>
        <li>Fixed crash bug related to DAO files.</li>
        <li>Removed all direct DAO calls in servlet code, replaced with business logic calls.</li>
      </ul>
    </td>
    <td><strong>August 4th, 2025</strong></td>
  </tr>
    <tr>
    <td><strong>3.1</strong></td>
    <td><strong>Lily S.</strong></td>
    <td>
      <ul>
        <li>Added role based access through session storage of logged in user roles; vehicle registration denies operators from accessing it.</li>
        <li>Implemented duplicate check prevention for vehicle number on vehicle registration page.</li>
        <li>DB adjustments for better info access.</li>
      </ul>
    </td>
    <td><strong>August 4th, 2025</strong></td>
  </tr>
    <tr>
    <td><strong>3.2</strong></td>
    <td><strong>Lily S.</strong></td>
    <td>
      <ul>
        <li><strong>Component Maintenance Servlet + all logic added (FR-05)</strong></li>
        <li>Role based access hides all scheduling buttons if user is not manager.</li>
        <li>
          <strong>Add maintenance scheduling page added + all logic (FR-05)</strong>
          <ul>
            <li>Button to this servlet only appears if repairs are needed for a component.</li>
            <li>Passes vehicle/component data from component page to auto-populate forms.</li>
          </ul>
        </li>
        <li>
          <strong>DB script updated</strong>
          <ul>
            <li>New notes column for maintenance.</li>
            <li>Auto default date to current time added for maintenance table.</li>
            <li>New note inserts for maintenance test data.</li>
          </ul>
        </li>
        <li>
          <strong>Observer pattern implemented to track # of components that need maintenance (FR-05)</strong>
          <ul>
            <li>Displayed on component page in red.</li>
          </ul>
        </li>
        <li>
          <strong>New DAO methods (with respective business logic calls)</strong>
          <ul>
            <li>Get All Components, Get All GPS, Get All Staff, Get Component by ID, Get Vehicle by ID</li>
          </ul>
        </li>
        <li>Edited dashboard page + get all logs DAO method to include new notes column.</li>
        <li>New Component DTO</li>
        <li>Various fixes / restructuring</li>
      </ul>
    </td>
    <td><strong>August 6th, 2025</strong></td>
  </tr>
       <tr>
  <td>3.3</td>
  <td>Khairunnisa Ashri</td>
  <td>
     <ul>
        <li>Completed FR04</li>
        <li>Added break log</li>
        <li>Observer on fuel report + alert on main page if logged in as a manager</li>
        <li>Role-based access implemented on dashboard</li>
        <li>Script, business logic, dao and daoimpl updated</li>
   </td>
  <td>August 6th, 2025</td>
</tr>
<tr>
  <td>3.4</td>
  <td>Francesca Parent</td>
  <td>Completed FR03, a new GPS tracking page, with the ability to add data, and to not accept invalid data</td>
  <td>August 6th, 2025</td>
</tr>
<tr>
  <td>3.5</td>
  <td>Oussema Faleh</td>
  <td>Completed ValidationException for business logic Operations</td>
  <td>August 6th, 2025</td>
</tr>
<tr>
  <td>3.6</td>
  <td>Oussema Faleh</td>
  <td>
     <ul>
        <li>Implemented JUnit Tests on many different aspects of the project including</li>
        <li>Dao operations</li>
        <li>Design pattern classes</li>
        <li>ValidationException cases</li>
      </ul>
   </td>
  <td>August 6th, 2025</td>
</tr>
<tr>
  <td>3.7</td>
  <td>Lily S.</td>
  <td>
     <ul>
        <li>Added JavaDoc comments to code I am responsible for</li>
        <li>Table of layers submission file finished</li>
        <li>UML Diagrams created for patterns personally worked on</li>
      </ul>
   </td>
  <td>August 8th, 2025</td>
</tr>
<tr>
  <td>3.8</td>
  <td>Khairunnisa Ashri</td>
  <td>
     <ul>
        <li>Checked tests</li>
        <li>fixed GPS insertion issue</li>
        <li>Added JavaDoc</li>
      </ul>
   </td>
  <td>August 8th, 2025</td>
</tr>
</table>


