class GlobIncludeProcessor < Asciidoctor::Extensions::IncludeProcessor
  def process doc, reader, target_glob, attributes
    Dir[File.join reader.dir, target_glob].sort.reverse_each do |target|
      content = IO.readlines target
      content.unshift '' unless attributes['adjoin-option']
      reader.push_include content, target, target, 1, attributes
    end
    reader
  end

  def handles? target
    target.include? '*'
  end
end

Asciidoctor::Extensions.register do
  include_processor GlobIncludeProcessor
end

