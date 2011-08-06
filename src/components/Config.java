
class Config {
String lastfile = "";

public Config() {

}

public void load() {

Properties p = new Properties();


this.lastfile = p.getProperty("lastfile", "default"); //how to do null?
}

public void save() {

Properties p = new Properties();

p.setProperty("lastfile", this.lastfile);
}

}