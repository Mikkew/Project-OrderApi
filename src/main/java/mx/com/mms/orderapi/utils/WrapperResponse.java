package mx.com.mms.orderapi.utils;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WrapperResponse<T> {
	private long count;
	private String prev;
	private String page;
	private String next;
	private T results;

	public WrapperResponse(Page<?> page, PagedModel<?> pagedModel, T result) {
		this.count = page.getTotalElements();
		this.prev = WrapperResponse.getURL(pagedModel.getPreviousLink());
		this.page= WrapperResponse.getURL(pagedModel.getLink("self"));
		this.next = WrapperResponse.getURL(pagedModel.getNextLink());
		this.results = result;
	}

	public ResponseEntity<Object> createResponse(HttpStatus status) {
		return new ResponseEntity<>(this, status);
	}
	
	private static String getURL(Optional<Link> link) {
		if (link.isPresent()) {
			UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(link.get().getHref());
			urlBuilder.replaceQueryParam("size", (Object[]) null).replaceQueryParam("sort", (Object[]) null).build();
			return urlBuilder.toUriString();
		}
		return null;
	}
}
