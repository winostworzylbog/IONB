
package pl.edu.uj.ii.ionb.airportmanager.web.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncFilter implements Filter {

    private FilterConfig filterConfig = null;
    
    public EncFilter() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

    // Return the filter configuration object for this filter. //
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    // Set the filter configuration object for this filter. //
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    // Destroy method for this filter //
    @Override
    public void destroy() {
        filterConfig = null;
    }

    // Init method for this filter //
    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    // Return a String representation of this object. //
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("EncFilter()");
        }
        StringBuilder sb = new StringBuilder("EncFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
}
