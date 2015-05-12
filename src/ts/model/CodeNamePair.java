package ts.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CodeNamePair")
// xml 文件的根元素
public class CodeNamePair {

	public String Code;
	public String Name;

	public CodeNamePair() {
	}

	public CodeNamePair(String code, String name) {
		Code = code;
		Name = name;
	}
}