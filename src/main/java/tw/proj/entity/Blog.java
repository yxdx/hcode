package tw.proj.entity;

import java.util.Date;
import java.util.Objects;

/**
 * 用于封装搜索结果
 *
 * @author ljc
 */
public class Blog {
	private String url;

	private String title;

	private String authorName;

	private String authorUrl;

	private String summary;

	private int pageviews;

	private Date postdate;


	public String getTitle() {
		return title;
	}

	public Blog setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public Blog setUrl(String url) {
		this.url = url;
		return this;
	}

	public String getAuthorName() {
		return authorName;
	}

	public Blog setAuthorName(String authorName) {
		this.authorName = authorName;
		return this;
	}

	public String getAuthorUrl() {
		return authorUrl;
	}

	public Blog setAuthorUrl(String authorUrl) {
		this.authorUrl = authorUrl;
		return this;
	}

	public String getSummary() {
		return summary;
	}

	public Blog setSummary(String summary) {
		this.summary = summary;
		return this;
	}

	public int getPageviews() {
		return pageviews;
	}

	public Blog setPageviews(int pageviews) {
		this.pageviews = pageviews;
		return this;
	}

	public Date getPostdate() {
		return postdate;
	}

	public Blog setPostdate(Date postdate) {
		this.postdate = postdate;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Blog)) {
			return false;
		}
		Blog blog = (Blog) o;
		return getPageviews() == blog.getPageviews() &&
				Objects.equals(getTitle(), blog.getTitle()) &&
				Objects.equals(getUrl(), blog.getUrl()) &&
				Objects.equals(getAuthorName(), blog.getAuthorName()) &&
				Objects.equals(getAuthorUrl(), blog.getAuthorUrl()) &&
				Objects.equals(getSummary(), blog.getSummary()) &&
				Objects.equals(getPostdate(), blog.getPostdate());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getTitle(), getUrl(), getAuthorName(), getAuthorUrl(), getSummary(), getPageviews(), getPostdate());
	}

	@Override
	public String toString() {
		return "Blog{" +
				"url='" + url + '\'' +
				", title='" + title + '\'' +
				", authorName='" + authorName + '\'' +
				", authorUrl='" + authorUrl + '\'' +
				", summary='" + summary + '\'' +
				", pageviews=" + pageviews +
				", postdate=" + postdate +
				'}';
	}
}
