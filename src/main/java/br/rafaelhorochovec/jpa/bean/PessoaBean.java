package br.rafaelhorochovec.jpa.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.rafaelhorochovec.jpa.dao.DaoGeneric;
import br.rafaelhorochovec.jpa.model.Pessoa;

@ViewScoped
@ManagedBean(name = "pessoaBean")
public class PessoaBean {
	
	private Pessoa pessoa = new Pessoa();
	private DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
	
	public String salvar() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		pessoa = daoGeneric.merge(pessoa);
		carregarPessoas();
		ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso,", pessoa.getNome() + " salva(o)."));
		return null;
	}

	public String remove() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso,", pessoa.getNome() + " removida(o)."));
		daoGeneric.deletePorId(pessoa);
		pessoa = new Pessoa();
		carregarPessoas();
		return null;
	}

	@PostConstruct
	public void carregarPessoas() {
		pessoas = daoGeneric.getListModel(Pessoa.class);
	}

	public String novo() {
		pessoa = new Pessoa();
		return null;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public DaoGeneric<Pessoa> getDaoGeneric() {
		return daoGeneric;
	}

	public void setDaoGeneric(DaoGeneric<Pessoa> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	
	public String getUrlPTBR() {		
		return "//cdn.datatables.net/plug-ins/1.10.12/i18n/Portuguese-Brasil.json";
	}
}
