package com.milkywire.impacterservice.dao;

import com.milkywire.impacterservice.domain.Post;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PostDao {

    private final String TABLE_NAME = "co_posts";

    @Inject
    JdbcTemplate jdbcTemplate;
    ;

    public Post find(long postId) {
        final String sql = "select * from " + TABLE_NAME + " WHERE post_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{postId}, new PostMapper());
        } catch (EmptyResultDataAccessException e) {
            return new Post();
        }
    }

    public List<Post> findAll() {
        final String sql = "select * from " + TABLE_NAME;

        return jdbcTemplate.query(sql, new PostMapper());
    }

    public List<Post> findAllForImpacter(long impacterId) {
        final String sql = "select * from " + TABLE_NAME + " WHERE impacter_id = ?";

        return jdbcTemplate.query(sql, new Object[]{impacterId}, new PostMapper());
    }

    public Post findPostForImpacter(long postId, long impacterId) throws EmptyResultDataAccessException {
        final String sql = "select * from " + TABLE_NAME + " WHERE post_id = ? AND impacter_id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new PostMapper(), new Object[]{postId, impacterId});
        } catch (EmptyResultDataAccessException e) {

            return new Post();
        }
    }

    public Post update(Post post) {
        final String sql = "UPDATE " + TABLE_NAME + " SET "
                + "description = ?, "
                + "type = ?, "
                + "status = ?, "
                + "data = ?::JSON, "
                + "reaction_count = ? "
                + "WHERE post_id = ?";

        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, post.getDescription());
            ps.setString(2, post.getType());
            ps.setString(3, post.getStatus());
            ps.setObject(4, post.getData());
            ps.setInt(5, post.getReactionCount());
            ps.setInt(6, post.getId());
            return ps;
        });

        return find(post.getId());
    }

    public void delete(long postId) {
        final String sql = "DELETE FROM " + TABLE_NAME + " WHERE post_id = ?";
        jdbcTemplate.update(sql, postId);
    }

    private static class PostMapper implements RowMapper<Post> {

        @Override
        public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
            Post post = new Post();
            post.setId(rs.getInt("post_id"));
            post.setDescription(rs.getString("description"));
            post.setType(rs.getString("type"));
            post.setStatus(rs.getString("status"));
            post.setData(rs.getString("data"));
            post.setReactionCount(rs.getInt("reaction_count"));
            post.setImpacterId(rs.getInt("impacter_id"));
            post.setPublishedAt(rs.getDate("published_at"));

            return post;
        }
    }
}
