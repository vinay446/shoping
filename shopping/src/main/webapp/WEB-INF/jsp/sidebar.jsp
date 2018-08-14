<div class="sidebar" data-color="purple"
	data-image="<c:url value="/resources/images/sidebar-1.jpg"/>">
	<!--
        Tip 1: You can change the color of the sidebar using: data-color="purple | blue | green | orange | red"

        Tip 2: you can also add an image using data-image tag
    -->
	<div class="logo">
		<p class="simple-text">Welcome ${username}</p>
	</div>
	<div class="sidebar-wrapper">
		<ul class="nav">
			<c:choose>
				<%-- -- Requested page is user profile --%>
				<c:when test="${page.equals('Profile')}">
					<li><a href="dashboard"> <i class="material-icons">dashboard</i>
							<p>Dashboard</p>
					</a></li>
					<li class="active"><a href="users"> <i
							class="material-icons">person</i>
							<p>User Profile</p>
					</a></li>
				</c:when>
				<%-- Default page  --%>
				<c:otherwise>
					<li class="active"><a href="dashboard"> <i
							class="material-icons">dashboard</i>
							<p>Dashboard</p>
					</a></li>
					<li><a href="users"> <i class="material-icons">person</i>
							<p>User Profile</p>
					</a></li>
				</c:otherwise>
			</c:choose>
			<!-- <li><a href="./table.html"> <i class="material-icons">content_paste</i>
					<p>Table List</p>
			</a></li>
			<li><a href="./typography.html"> <i class="material-icons">library_books</i>
					<p>Typography</p>
			</a></li>
			<li><a href="./icons.html"> <i class="material-icons">bubble_chart</i>
					<p>Icons</p>
			</a></li>
			<li><a href="./maps.html"> <i class="material-icons">location_on</i>
					<p>Maps</p>
			</a></li>
			<li><a href="./notifications.html"> <i
					class="material-icons text-gray">notifications</i>
					<p>Notifications</p>
			</a></li> -->
		</ul>
	</div>
</div>