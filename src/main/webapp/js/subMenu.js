var openSubMenu = false;
var subMenuClass = document.getElementsByClassName("navbar-small")[0];
var subMenuButton = subMenuClass.getElementsByTagName("div")[0].getElementsByTagName("button")[0];
var subMenu = document.getElementById("sub-menu-container");

function showSubMenu(){
	if(openSubMenu == false){
		subMenuButton.onmouseout = function(){
			this.style.backgroundColor = "#00ccad";
		}
		subMenuButton.onmouseover = function(){
			this.style.backgroundColor = "#00ccad";
		}
		subMenuClass.getElementsByTagName("div")[0].style.borderRadius = "7px 7px 7px 0";
		subMenu.style.display = "block";
		openSubMenu = true;	
	}
	else{
		subMenuButton.onmouseout = function(){
			this.style.backgroundColor = "#00a990";
		}
		subMenuButton.onmouseover = function(){
			this.style.backgroundColor = "#00ccad";
		}
		subMenuClass.getElementsByTagName("div")[0].style.borderRadius = "7px";
		subMenu.style.display = "none";
		openSubMenu = false;
	}
	
}