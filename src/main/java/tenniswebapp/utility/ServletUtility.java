package tenniswebapp.utility;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ServletUtility {

	private static final String E1="Error1";
	private static final String E2="Error2";
	private static final String E3="Error3";
	private static final String E4="Error4";
	private static final String E5="Error5";
	private static final String E6="Error6";
	private static final String E7="Error7"; 
	private static final String E8="Error8";
	private static final String E9="Error9";
	private static final String E10="Error10";
	private static final String E11="Error11";
	private static final String E12="Error12";
	private static final String E13="Error13";
	private static final String E14="Error14";
	private static final String E15="Error15";
	private static final String E16="Error16";
	private static final String E17="Error17";
	private static final String E18="Error18";
	private static final String E19="Error19";
	private static final String E20="Error20";
	private static final String E21="Error21";
	private static final String E22="Error22";
	private static final String E23="Error23";
	private static final String E24="Error24";
	private static final String E25="Error25";
	private static final String E26="Error26";
	private static final String E27="Error27";
	private static final String E28="Error28";
	private static final String E29="Error29";
	private static final String E30="Error30";
	private static final String E31="Error31";
	private static final String E32="Error32";
	private static final String E33="Error33";
	private static final String E34="Error34";



	


    private static final String SucessMessage = "Done";

  public static void forward(String page, HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    RequestDispatcher rd = request.getRequestDispatcher(page);
    System.out.println(page);
    rd.forward(request, response); 
  }

  public static void redirect(String page, HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    response.sendRedirect(page);
  } 


  public static void setErrorName(String msg, HttpServletRequest request) {
    request.setAttribute(E1, msg);
  }

  public static String getErrorName(HttpServletRequest request) {
   String val= (String) request.getAttribute(E1);
   if (val == null) {
	      return "";
	    } else {
	      return val;
	    }
  }
  public static void setErrorSurname(String msg, HttpServletRequest request) {
	    request.setAttribute(E2, msg);
	  }

	  public static String getErrorSurname(HttpServletRequest request) {
		  String val= (String) request.getAttribute(E2);
	   if (val == null) {
		      return "";
		    } else {
		      return val;
		    }

	  }
	  public static void setErrorDOB(String msg, HttpServletRequest request) {
		    request.setAttribute(E3, msg);
		  }

		  public static String getErrorDOB(HttpServletRequest request) {
			  String val= (String) request.getAttribute(E3);
			  if (val == null) {
			      return "";
			    } else {
			      return val;
			    }

		  }
	 public static void setErrorPoints(String msg, HttpServletRequest request) {
			    request.setAttribute(E4, msg);
			  }

	public static String getErrorPoints(HttpServletRequest request) {
		 String val= (String) request.getAttribute(E4);
		if (val == null) {
		      return "";
		    } else {
		      return val;
		    }
			  }

	 public static void setErrorTournament(String msg, HttpServletRequest request) {
		    request.setAttribute(E5, msg);
		  }

public static String getErrorTournament(HttpServletRequest request) {
	 String val= (String) request.getAttribute(E5);
	if (val == null) {
	      return "";
	    } else {
	      return val;
	    }

		  }
public static void setErrorFullTournament(String msg, HttpServletRequest request) {
    request.setAttribute(E6, msg);
  }

public static String getErrorFullTournament(HttpServletRequest request) {
	 String val= (String) request.getAttribute(E6);
	if (val == null) {
	      return "";
	    } else {
	      return val;
	    }
  

  }
public static void setErrorAdminExists(String msg, HttpServletRequest request) {
    request.setAttribute(E7, msg);
  }

public static String getErrorAdminExists(HttpServletRequest request) {
	 String val= (String) request.getAttribute(E7);
	if (val == null) {
	      return "";
	    } else {
	      return val;
	    }

  }
public static void setErrorUsernameExists(String msg, HttpServletRequest request) {
    request.setAttribute(E8, msg);
  }

public static String getErrorUsernameExists(HttpServletRequest request) {
	 String val= (String) request.getAttribute(E8);
    if (val == null) {
        return "";
      } else {
        return val;
      }


  }
public static void setErrorPassword(String msg, HttpServletRequest request) {
    request.setAttribute(E9, msg);
  }

public static String getErrorPassword(HttpServletRequest request) {
	 String val= (String) request.getAttribute(E9);
    if (val == null) {
        return "";
      } else {
        return val;
      }
   

  }
public static void setErrorConfirmedPassword(String msg, HttpServletRequest request) {
    request.setAttribute(E10, msg);
  }

public static String getErrorConfirmedPassword(HttpServletRequest request) {
	 String val= (String) request.getAttribute(E10);
    if (val == null) {
        return "";
      } else {
        return val;
      }
  }
public static void setErrorLogin(String msg, HttpServletRequest request) {
    request.setAttribute(E11, msg);
  }

public static String getErrorLogin(HttpServletRequest request) {
	 String val= (String) request.getAttribute(E11);
    if (val == null) {
        return "";
      } else {
        return val;
      }
}
public static void setErrorSorteggi(String msg, HttpServletRequest request) {
    request.setAttribute(E12, msg);
  }

public static String getErrorSorteggi(HttpServletRequest request) {
	 String val= (String) request.getAttribute(E12);
    if (val == null) {
        return "";
      } else {
        return val;
      }
  }

public static void setErrorRisultatoPartita1(String msg, HttpServletRequest request) {
    request.setAttribute(E13, msg);
  }

public static String getErrorRisultatoPartita1(HttpServletRequest request) {
	 String val= (String) request.getAttribute(E13);
    if (val == null) {
        return "";
      } else {
        return val;
      }
  }
 public static void setErrorRisultatoPartita2(String msg, HttpServletRequest request) {
	    request.setAttribute(E14, msg);
	  }

public static String getErrorRisultatoPartita2(HttpServletRequest request) {
		 String val= (String) request.getAttribute(E14);
	    if (val == null) {
	        return "";
	      } else {
	        return val;
	      }
	  }
public static void setErrorRisultatoPartita3(String msg, HttpServletRequest request) {
		    request.setAttribute(E15, msg);
		  }

public static String getErrorRisultatoPartita3(HttpServletRequest request) {
			 String val= (String) request.getAttribute(E15);
		    if (val == null) {
		        return "";
		      } else {
		        return val;
		      }
		  }
 public static void setErrorRisultatoPartita4(String msg, HttpServletRequest request) {
			    request.setAttribute(E16, msg);
			  }

public static String getErrorRisultatoPartita4(HttpServletRequest request) {
				 String val= (String) request.getAttribute(E16);
			    if (val == null) {
			        return "";
			      } else {
			        return val;
			      }
			  }
public static void setErrorRisultatoPartita5(String msg, HttpServletRequest request) {
	 request.setAttribute(E17, msg);
				  }

public static String getErrorRisultatoPartita5(HttpServletRequest request) {
	String val= (String) request.getAttribute(E17);
	if (val == null) {
	 return "";
     } else {
	return val;
     }
	}
public static void setErrorRisultatoPartita6(String msg, HttpServletRequest request) {
request.setAttribute(E18, msg);
 }

public static String getErrorRisultatoPartita6(HttpServletRequest request) {
String val= (String) request.getAttribute(E18);
if (val == null) {
return "";
} else {
return val;
	}
}
public static void setErrorRisultatoPartita7(String msg, HttpServletRequest request) {
 request.setAttribute(E19, msg);
}

public static String getErrorRisultatoPartita7(HttpServletRequest request) {
String val= (String) request.getAttribute(E19);
if (val == null) {
     return "";
     } else {
	return val;
}
}
public static void setErrorAdminwithPoints(String msg, HttpServletRequest request) {
    request.setAttribute(E20, msg);
  }

public static String getErrorAdminwithPoints(HttpServletRequest request) {
	 String val= (String) request.getAttribute(E20);
    if (val == null) {
        return "";
      } else {
        return val;
      }
}

public static void setVincoQuarti(String msg, HttpServletRequest request) {
    request.setAttribute(E21, msg);
 }

public static String getVincoQuarti(HttpServletRequest request) {
	 String val= (String) request.getAttribute(E21);
   if (val == null) {
       return "";
     } else {
       return val;
     }
 }
public static void setPerdoQuarti(String msg, HttpServletRequest request) {
	    request.setAttribute(E22, msg);
	  }

public static String getPerdoQuarti(HttpServletRequest request) {
		 String val= (String) request.getAttribute(E22);
	    if (val == null) {
	        return "";
	      } else {
	        return val;
	      }
	  }
public static void setVincoSemifinali(String msg, HttpServletRequest request) {
		    request.setAttribute(E23, msg);
		  }

public static String getVincoSemifinali(HttpServletRequest request) {
			 String val= (String) request.getAttribute(E23);
		    if (val == null) {
		        return "";
		      } else {
		        return val;
		      }
		  }
public static void setPerdoSemifinali(String msg, HttpServletRequest request) {
			    request.setAttribute(E24, msg);
			  }

public static String getPerdoSemifinali(HttpServletRequest request) {
				 String val= (String) request.getAttribute(E24);
			    if (val == null) {
			        return "";
			      } else {
			        return val;
			      }
			  }
public static void setVincoFinale(String msg, HttpServletRequest request) {
	 request.setAttribute(E25, msg);
				  }

public static String getVincoFinale(HttpServletRequest request) {
	String val= (String) request.getAttribute(E25);
	if (val == null) {
	 return "";
    } else {
	return val;
    }
	}
public static void setPerdoFinale(String msg, HttpServletRequest request) {
request.setAttribute(E26, msg);
}

public static String getPerdoFinale(HttpServletRequest request) {
String val= (String) request.getAttribute(E26);
if (val == null) {
return "";
} else {
return val;
	}
}
public static void setErrorQuantità(String msg, HttpServletRequest request) {
request.setAttribute(E27, msg);
}

public static String getErrorQuantità(HttpServletRequest request) {
String val= (String) request.getAttribute(E27);
if (val == null) {
return "";
} else {
return val;
	}
}
public static void setErrorTipologia(String msg, HttpServletRequest request) {
request.setAttribute(E28, msg);
}

public static String getErrorTipologia(HttpServletRequest request) {
String val= (String) request.getAttribute(E28);
if (val == null) {
return "";
} else {
return val;
	}
}
public static void setErrorUsernameFormat(String msg, HttpServletRequest request) {
request.setAttribute(E29, msg);
}

public static String getErrorUsernameFormat(HttpServletRequest request) {
String val= (String) request.getAttribute(E29);
if (val == null) {
return "";
} else {
return val;
	}
}
public static void setErrorTipologiaFormat(String msg, HttpServletRequest request) {
request.setAttribute(E30, msg);
}

public static String getErrorTipologiaFormat(HttpServletRequest request) {
String val= (String) request.getAttribute(E30);
if (val == null) {
return "";
} else {
return val;
	}
}
public static void setErrorTorneoCompletato(String msg, HttpServletRequest request) {
request.setAttribute(E31, msg);
}

public static String getErrorTorneoCompletato(HttpServletRequest request) {
String val= (String) request.getAttribute(E31);
if (val == null) {
return "";
} else {
return val;
	}
}
public static void setErrorSorteggiNonRegistrati(String msg, HttpServletRequest request) {
request.setAttribute(E32, msg);
}

public static String getErrorSorteggiNonRegistrati(HttpServletRequest request) {
String val= (String) request.getAttribute(E32);
if (val == null) {
return "";
} else {
return val;
	}
}
public static void setErrorSemifinaliNonRegistrate(String msg, HttpServletRequest request) {
request.setAttribute(E33, msg);
}

public static String getErrorSemifinaliNonRegistrate(HttpServletRequest request) {
String val= (String) request.getAttribute(E33);
if (val == null) {
return "";
} else {
return val;
	}
}
public static void setErrorQuartiNonRegistrati(String msg, HttpServletRequest request) {
request.setAttribute(E34, msg);
}

public static String getErrorQuartiNonRegistrati(HttpServletRequest request) {
String val= (String) request.getAttribute(E34);
if (val == null) {
return "";
} else {
return val;
	}
}


  public static String getParameter(String property, HttpServletRequest request) {
    String val = (String) request.getParameter(property);
    if (val == null) {
      return "";
    } else {
      return val;
    }
  }
  


  

}
