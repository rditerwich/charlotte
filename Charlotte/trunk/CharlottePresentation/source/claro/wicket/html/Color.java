package claro.wicket.html;

import java.io.Serializable;

public class Color implements Serializable{
	private static final long serialVersionUID = 5208079725869005885L;
		int red=0;
		int green=0;
		int blue=0;
		private boolean rgb = true;
		private String color = "";
		public Color(int R,int G,int B){
			red=R;
			green=G;
			blue=B;
		}
		
		public Color(String color){
			rgb=false;
			this.color=color;
		}
		
		@Override
		public String toString() {
			if(rgb){
			  return "rgb("+red+", "+green+", "+blue+")";
			}
			else{
				return color;
			}
		}

}
